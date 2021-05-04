// Databricks notebook source
// MAGIC %md
// MAGIC 
// MAGIC # LGS Retail Data Wrangling and Analytics Notebook

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Configuration and Imports

// COMMAND ----------

import org.apache.spark.sql.functions._
import org.apache.spark.sql.DataFrameStatFunctions
import org.apache.spark.sql.expressions._
import org.apache.spark.ml.feature._

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Load Data from PSQL into Spark DataFrame
// MAGIC 
// MAGIC The table `lgs_retail` is loaded from the CSV file via the Databricks UI, and we will read it into a Spark DataFrame.

// COMMAND ----------

val retailDf = spark.read.table("lgs_retail")
retailDf.printSchema()
retailDf.cache()

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Total Invoice Amount Distribution
// MAGIC 
// MAGIC ### 1. Get the invoice amounts grouped by invoice_no

// COMMAND ----------

// Create a view with only relevant invoice data.
val requiredColDf = spark.sql("""
  SELECT invoice_no, quantity * unit_price AS amount
  FROM lgs_retail
  WHERE quantity * unit_price > 0
  ORDER BY invoice_no
""")
val amountsDf = requiredColDf
  .groupBy("invoice_no")
  .sum()
  .withColumnRenamed("sum(amount)", "total_amount")
  .orderBy("total_amount")

display(amountsDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Showing the distribution of invoice amount for the full dataset

// COMMAND ----------

amountsDf.createOrReplaceTempView("amountsDf")
val amountStasDf = spark.sql("""
  SELECT avg(total_amount), max(total_amount), min(total_amount)
  FROM amountsDf
""")

val medianIndex = (amountsDf.count() / 2).toInt
val median = amountsDf
  .take(medianIndex)
  .last
  .get(1)

val mode = amountsDf
  .groupBy("total_amount")
  .count()
  .sort(desc("count"))
  .first
  .get(0)

// COMMAND ----------

display(amountStasDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 3. Showing the distribution of invoice amount for the first 85 quantiles

// COMMAND ----------

val eightyFive = amountsDf.stat.approxQuantile("total_amount", Array(0.85), 0)(0)
val quantDf = amountsDf.filter($"total_amount" < eightyFive)
display(quantDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Monthly Placed and Canceled Orders
// MAGIC 
// MAGIC ### 1. Getting the invoices for each month

// COMMAND ----------

val orderDf = retailDf.select($"invoice_no", date_format($"invoice_date", "yyyyMM").alias("yyMM"))
display(orderDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Create a new dataframe with monthly total orders and cancelled orders

// COMMAND ----------

orderDf.createOrReplaceTempView("orderDf")
val monthlyTotal = spark.sql("""
  SELECT yymm, count(DISTINCT invoice_no) AS total_orders
  FROM orderDf
  GROUP BY yymm
  ORDER BY yymm
""")

display(monthlyTotal)

// COMMAND ----------

val cancelled = orderDf.filter($"invoice_no".like("C%"))
cancelled.createOrReplaceTempView("cancelled")
val cancelledDf = spark.sql("""
  SELECT yymm, count(DISTINCT invoice_no) AS cancelled_orders
  FROM cancelled
  GROUP BY yymm
""")

val allOrders = monthlyTotal
  .join(cancelledDf, monthlyTotal("yymm") === cancelledDf("yymm"), "inner")
  .withColumn("completed_order", col("total_orders") - col("cancelled_orders"))
display(allOrders)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Monthly Sales
// MAGIC ### 1. Getting the sales records for each month

// COMMAND ----------

val salesDf = retailDf
  .select($"invoice_no", $"quantity", $"unit_price", 
          date_format($"invoice_date", "yyyyMM").alias("yyMM"),
          ($"quantity" * $"unit_price").alias("amount"))

display(salesDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Compute the monthly sales 

// COMMAND ----------

val monthlySales = salesDf
  .groupBy("yyMM").sum()
  .withColumnRenamed("sum(amount)", "total_sales")
  .select("yyMM", "total_sales").sort("yyMM")

display(monthlySales)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Monthly Sales Growth
// MAGIC ### 1. Compute the monthly sales growth percentage using window and lag

// COMMAND ----------

val tempWind = Window.partitionBy().orderBy("yyMM")
var growthDf = monthlySales
  .withColumn("prev", lag("total_sales", 1).over(tempWind))
  .withColumn("growth", when(isnull($"total_sales" - $"prev"), 0).otherwise($"total_sales" - $"prev"))
  .withColumn("pct_growth", $"growth"/$"prev")

display(growthDf.select("yyMM", "total_sales", "pct_growth"))

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Monthly Active Users
// MAGIC ### 1. Getting the active user invoice records for each month

// COMMAND ----------

val usersDf = retailDf.select($"customer_id", $"invoice_date", date_format($"invoice_date", "yyyyMM").alias("yyMM"))

display(usersDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Compute the monthly number of active user

// COMMAND ----------

usersDf.createOrReplaceTempView("usersDf")
val activeUsers = spark.sql("""
  SELECT yyMM, count(DISTINCT customer_id) AS active_customers
  FROM usersDf
  GROUP BY yymm
  ORDER BY yymm
""")

display(activeUsers)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## New and Existing Users
// MAGIC ### 1. Getting the month of the first invoice for each customer

// COMMAND ----------

val firstMonth = usersDf
  .select($"customer_id", $"yyMM".cast("Int"))
  .groupBy("customer_id").min("yyMM")
  .withColumnRenamed("min(yyMM)", "first_month")
  .withColumnRenamed("customer_id", "customer")

display(firstMonth)

// COMMAND ----------

val allInvoicesDf = usersDf
  .join(firstMonth, usersDf("customer_id") === firstMonth("customer"), "inner")
  .select("customer_id", "yyMM", "first_month")

display(allInvoicesDf)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Getting the new users and existing users count for each month

// COMMAND ----------

// Get the number of new users for each month
allInvoicesDf.createOrReplaceTempView("allInvoicesDf")
var newUsers = spark.sql("""
  SELECT yyMM, count(DISTINCT customer_id) AS no_new_users
  FROM allInvoicesDf
  WHERE yyMM == first_month
  GROUP BY yyMM
  ORDER BY yyMM
""")

display(newUsers)

// COMMAND ----------

// Get the total number of active users for each month
var activeUsers = spark.sql("""
  SELECT yyMM, count(DISTINCT customer_id) AS no_active_users
  FROM allInvoicesDf
  GROUP BY yyMM
  ORDER BY yyMM
""")

display(activeUsers)

// COMMAND ----------

// Get the number of existing users for each month by subtracting new users from all active users
var allUsers = activeUsers
  .join(newUsers, activeUsers("yyMM")===newUsers("yyMM"))
  .sort(activeUsers("yyMM"))
  .withColumn("existing_users", $"no_active_users" - $"no_new_users")
  .select(activeUsers("yyMM"), $"no_active_users", $"no_new_users", $"existing_users")

display(allUsers)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Finding RFM Table and RFM Segmentation
// MAGIC ### 1. Data Preparation

// COMMAND ----------

// Diaplay summary statistics of unit_price, quantity, and amount
val original = retailDf
  .na.drop()
  .select($"invoice_no", $"customer_id", $"quantity", $"unit_price", $"invoice_date")
original.createOrReplaceTempView("original")

val raw = spark.sql("""
  SELECT customer_id, unit_price, quantity, unit_price * quantity AS amount, invoice_no, invoice_date
  FROM original
  WHERE quantity > 0 AND unit_price * quantity > 0
""")

display(raw.summary())

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Getting last invoice date, number of orders, total amount as recency, frequence, and monetary

// COMMAND ----------

// Summing total invoice amount for each customer for MONETARY
val rfmM = raw
  .groupBy("customer_id")
  .sum("amount")
  .withColumnRenamed("sum(amount)", "monetary")
  .sort("customer_id")

display(rfmM)

// COMMAND ----------

// Getting latest order date for each customer for RECENCY
val recency = raw
  .withColumn("recency", datediff(to_date(lit("2012-01-01")), to_date(date_format($"invoice_date", "yyyy-MM-dd"))) - 1)
  .groupBy("customer_id")
  .min("recency")
  .sort("customer_id")
  .withColumnRenamed("min(recency)", "recency")

val rfmMR = rfmM
  .join(recency, rfmM("customer_id")===recency("customer_id"), "inner")
  .select(rfmM("customer_id"), $"recency", rfmM("monetary"))
  .sort("customer_id")

display(rfmMR)

// COMMAND ----------

// Finding the total number of invoices for each customer as FREQUENCY
val frequency = spark.sql("""
  SELECT customer_id, count(DISTINCT invoice_no) AS frequency
  FROM original
  GROUP BY customer_id
  ORDER BY customer_id
""")

val rfmComplete = rfmMR
  .join(frequency, rfmMR("customer_id")===frequency("customer_id"), "inner")
  .select(rfmMR("customer_id"), $"recency", $"monetary", $"frequency")
  .sort("customer_id")

display(rfmComplete)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 3. Finding RFM Score for all customers

// COMMAND ----------

// Confiuring QuantileDiscretizer for all three of RFM
val monDiscretizer = new QuantileDiscretizer()
  .setInputCol("monetary")
  .setOutputCol("monetary_score")
  .setNumBuckets(5)
val freDiscretizer = new QuantileDiscretizer()
  .setInputCol("frequency")
  .setOutputCol("frequency_score")
  .setNumBuckets(5)
val recDiscretizer = new QuantileDiscretizer()
  .setInputCol("recency")
  .setOutputCol("recency_score")
  .setNumBuckets(5)

// Split RFM into buckets and concatenate in one table
val rfmMScore = monDiscretizer.fit(rfmComplete).transform(rfmComplete)
val rfmMFScore = freDiscretizer.fit(rfmMScore).transform(rfmMScore)
val rfmTemp = recDiscretizer.fit(rfmMFScore).transform(rfmMFScore)

rfmTemp.createOrReplaceTempView("rfmTemp")
val rfmTable = spark.sql("""
  SELECT customer_id, recency, frequency, monetary,
      monetary_score + 1 AS monetary_score, 
      abs(frequency_score - 5) AS frequency_score, 
      recency_score + 1 AS recency_score
  FROM rfmTemp
""")
display(rfmTable)

// COMMAND ----------

// Combining the 3 score columns in 1 column
val rfmCast = rfmTable
  .withColumn("recency_score_int", $"recency_score".cast("Int"))
  .withColumn("frequency_score_int", $"frequency_score".cast("Int"))
  .withColumn("monetary_score_int", $"monetary_score".cast("Int"))

val rfmScores = rfmCast
  .withColumn("RFM_score", concat($"recency_score_int".cast("String"), $"frequency_score_int".cast("String"), $"monetary_score_int".cast("String")))
  .select("customer_id", "recency", "frequency", "monetary", "recency_score", "frequency_score", "monetary_score", "RFM_score")

display(rfmScores)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 4. Segmenting customers into 11 categories and show results

// COMMAND ----------

// Define a UDF to convert a RFM score combination to a customer category
val segment = (score: String) => {
  if (score.matches("[1-2][1-2].")) "Hibernating"
  else if (score.matches("[1-2][1-2].")) "At Risk"
  else if (score.matches("[1-2]5.")) "Can't Lose"
  else if (score.matches("3[1-2].")) "About to Sleep"
  else if (score.matches("33.")) "Need Attention"
  else if (score.matches("[3-4][4-5].")) "Loyal Customers"
  else if (score.matches("41.")) "Promising"
  else if (score.matches("51.")) "New Customers"
  else if (score.matches("[4-5][2-3].")) "Potential Loyalists"
  else "Champions"
}
spark.udf.register("segment", segment)

rfmScores.createOrReplaceTempView("rfmScores")
val rfmSegmentation = spark.sql("""
  SELECT customer_id, recency, frequency, monetary,
    recency_score, frequency_score, monetary_score, RFM_score, segment(RFM_score) AS segment
  FROM rfmScores
""")

display(rfmSegmentation)

// COMMAND ----------

val rfmResults = rfmSegmentation
  .groupBy("segment")
  .agg(avg($"recency").alias("mean_recency"), 
       avg($"frequency").alias("mean_frequency"),
       avg($"monetary").alias("mean_monetary"),
       count($"segment"))

display(rfmResults)
