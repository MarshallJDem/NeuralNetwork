# NeuralNetwork
Neural network and big data research project that is made to be fully variable and customizable, written in Java.

The class NeuralNetworkF is the main class and is a feed-forward neural network using back propogation that has customizable variables, such as:
  - Number of Hidden Layer
  - Number of neurons
  - Learning/momentum weights
  - Togglable functions (such as learning rates that decrease over time)
  - Input/Output setup
  - Etc.

An example use of the neural network is given which predicts stock trends in a game called Runescape which has an in-game stock market. XMLParser contains files for working with big data that first finds a list of all item names, then goes through the runescape wiki, using https request to GET the stock price data. The data is then formatted and sent as training data to the neural network.

This is a somewhat underdeveloped project that I made as a research project - if you are interested in using it for any reason let me know and I can document it better.
