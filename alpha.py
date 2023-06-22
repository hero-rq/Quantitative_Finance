import pandas as pd
import numpy as np
from keras.models import Sequential
from keras.layers import LSTM, Dense
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import mean_squared_error

# Load data
df = pd.read_csv('/kaggle/input/google-stock-data/GOOGL.csv')

# Assume you're predicting 'Close' prices
dataset = df['Close'].values
dataset = dataset.astype('float32')

# Rescale the data from 0 to 1
scaler = MinMaxScaler(feature_range=(0, 1))
dataset = scaler.fit_transform(np.reshape(dataset, (-1, 1)))

# Split the data into training and test sets
train_size = int(len(dataset) * 0.67)
test_size = len(dataset) - train_size
train, test = dataset[0:train_size,:], dataset[train_size:len(dataset),:]

# Helper function to create a dataset
def create_dataset(dataset, look_back=1):
    dataX, dataY = [], []
    for i in range(len(dataset)-look_back-1):
        a = dataset[i:(i+look_back), 0]
        dataX.append(a)
        dataY.append(dataset[i + look_back, 0])
    return np.array(dataX), np.array(dataY)

# Create datasets for the model
look_back = 1
trainX, trainY = create_dataset(train, look_back)
testX, testY = create_dataset(test, look_back)

# Reshape the input to be [samples, time steps, features] which is required for LSTM
trainX = np.reshape(trainX, (trainX.shape[0], 1, trainX.shape[1]))
testX = np.reshape(testX, (testX.shape[0], 1, testX.shape[1]))

# Define and fit the LSTM model
model = Sequential()
model.add(LSTM(4, input_shape=(1, look_back)))
model.add(Dense(1))
model.compile(loss='mean_squared_error', optimizer='adam')
model.fit(trainX, trainY, epochs=100, batch_size=1, verbose=2)

# Make predictions
trainPredict = model.predict(trainX)
testPredict = model.predict(testX)

# Invert the predictions back to the original scale
trainPredict = scaler.inverse_transform(trainPredict)
trainY = scaler.inverse_transform([trainY])
testPredict = scaler.inverse_transform(testPredict)
testY = scaler.inverse_transform([testY])

import matplotlib.pyplot as plt

# Plot the original data
plt.plot(trainY.flatten(), label='Original Train Data')
plt.plot(testY.flatten(), label='Original Test Data')

# Plot the predicted values
plt.plot(trainPredict.flatten(), label='Train Predictions')
plt.plot(testPredict.flatten(), label='Test Predictions')

# Set plot labels and title
plt.xlabel('Time')
plt.ylabel('Price')
plt.title('Stock Price Predictions')

# Add legend
plt.legend()

# Show the plot
plt.show()
