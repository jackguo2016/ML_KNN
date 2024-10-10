# KNN Algorithm for Diabetes Prediction

This project implements the K-Nearest Neighbors (KNN) algorithm to predict diabetes based on various health metrics. The implementation includes data preprocessing, model training, prediction, and performance evaluation.

## Project Overview

The KNN algorithm is used to classify whether a patient has diabetes or not, based on features such as glucose level, blood pressure, BMI, etc. The project also includes visualization of results and error rate analysis for different K values.

本项目实现了基于K-最近邻(KNN)算法的糖尿病预测模型。通过分析包括怀孕次数、葡萄糖水平、血压、BMI等健康指标,预测患者是否患有糖尿病。项目包含数据预处理、模型训练、预测和性能评估等功能,并提供了错误率分析和数据可视化。使用Java开发,主要文件包括Main.java(核心逻辑)、Data.java(数据处理)和graph.java(可视化)。项目使用diabetes.csv作为训练数据,testt.csv作为测试数据。通过运行Main.java文件即可执行完整的分析过程。该项目旨在帮助研究人员和开发者更好地理解和应用KNN算法在医疗诊断领域的应用。

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [File Structure](#file-structure)
- [Algorithm](#algorithm)
- [Dataset](#dataset)
- [Performance Evaluation](#performance-evaluation)
- [Visualization](#visualization)

## Installation

To run this project, you need Java and the following libraries:

- JFreeChart (for data visualization)

Ensure you have Java Development Kit (JDK) installed on your system.

## Usage

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/ML_KNN.git
   ```

2. Navigate to the project directory:
   ```
   cd ML_KNN
   ```

3. Compile and run the Main.java file:
   ```
   javac Main.java
   java Main
   ```

## File Structure

- `Main.java`: Contains the main logic for data loading, KNN implementation, and result visualization.
- `Data.java`: Defines the Data class for storing and managing individual data points.
- `graph.java`: Handles the creation of line charts for visualizing error rates.
- `diabetes.csv`: The main dataset used for training.
- `testt.csv`: The test dataset used for evaluation.

## Algorithm

The KNN Algorithm steps:

1. Load the data
2. Initialize K to your chosen number of neighbors
3. For each example in the data:
   3.1 Calculate the distance between the query example and the current example from the data.
   3.2 Add the distance and the index of the example to an ordered collection
4. Sort the ordered collection of distances and indices from smallest to largest by the distances
5. Pick the first K entries from the sorted collection
6. Get the labels of the selected K entries
7. If regression, return the mean of the K labels
8. If classification, return the mode of the K labels

## Dataset

The project uses two CSV files:

1. `diabetes.csv`: Contains the training data with features like Pregnancies, Glucose, BloodPressure, SkinThickness, Insulin, BMI, DiabetesPedigreeFunction, Age, and Outcome.
2. `testt.csv`: Contains the test data with the same features as the training data.

## Performance Evaluation

The program calculates and displays:
- Error rate for different K values
- Confusion matrix
- Accuracy

## Visualization

The project includes two types of visualizations:
1. A line chart showing the relationship between K values and error rates.
2. A scatter plot displaying the relationship between Pregnancies and Insulin, color-coded by outcome.

## Contributing

Contributions to improve the project are welcome. Please feel free to fork the repository and submit pull requests.

## License

This project is open source and available under the [MIT License](LICENSE).

# ML_KNN
The KNN algorithm assumes that similar things exist in close proximity. In other words, similar things are near to each other.
The KNN Algorithm
1.Load the data

2. Initialize K to your chosen number of neighbors

3. For each example in the data

3.1 Calculate the distance between the query example and the current example from the data.

3.2 Add the distance and the index of the example to an ordered collection

4. Sort the ordered collection of distances and indices from smallest to largest (in ascending order) by the distances

5. Pick the first K entries from the sorted collection

6. Get the labels of the selected K entries

7. If regression, return the mean of the K labels

8. If classification, return the mode of the K labels
