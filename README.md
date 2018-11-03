# NeuralNetwork
Huge neural network and big data research project that predicts runescape stock trends, written in Java.

The class NeuralNetworkF is the main class and is a feed-forward neural network that has customizable variables, such as:
  - Number of Hidden Layer
  - Number of neurons
  - Learning/momentum weights
  - Togglable functions (such as learning rates that decrease over time)
  - Input/Output setup
  - Etc.

In the _ folder is all of the required resources for grabbing the history of stock prices from the runescape in-game stock market. First it finds a list of all item names, then it goes through the runescape wiki, using https request to GET the stock price data. This is then formatted and sent as training data to the neural network.

The main usable part of this project however is simply the neural network. The runescape stock price is just an application of it. 

This is a somewhat underdeveloped project that I made as a research project - if you are interested in using it for any reason let me know and I can document it better.
