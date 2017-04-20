What is Document Distance?

Consider that you have two documents containing a huge amount of text in them, be it essays or websites. Now you want to know how similar these documents are, in the sense of: how many words overlap in these documents. Conceptual the algorithm is really simple there's just a few steps that you'll have to go through:

Open and read both documents that you are going to compare. Only read words and numbers, skip special characters (spaces, dots, etc..) and convert the words to lower case
Calculate the word frequency in both collections of words, this means how many times each word occur in each document
Compare the frequencies from both computations and calculate the distance
The distance itself is calculated using a predefined formula that you don't really have to pay too much attention too at this moment, unless you really fancy computations on vectors.

So, the algoritham is quite simple, it consists from 4 steps:

1) Read file
2) Make word list (divide file into words)
3) Count frequencies of words
4) Compute dot product
    – for every word in the first document, check if it
    appears in the other document; if yes, multiply their
    frequencies and add to the dot product

I was inspired by toturial on: https://www.filipekberg.se/2014/02/17/calculating-document-distance/ so I wanted to implement it in Java.
