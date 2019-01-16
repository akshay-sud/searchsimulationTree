# searchsimulationTree
Simulation of search engine, please look at documentation for a more detailed summary than this

This program takes a keyword and a website, and uses a Java web crawler (not written by me, credit given to its creator in
the documentation) in order to pull webpages on the given website that involve the keyword in some way. Once 30 URLs have been
extracted from the website, they are used to form 30 pageRank objects.
A pageRank object contains 6 (simulated) factors that a real URL result on a search engine might have. The first 4 factors are:
- frequency of traffic
- how long it has existed
- its relevance to the keyword
- how much the domain pays the search engine

The last 2 factors are unused in the Tree balancing, but are instead used for linear regression via Gradient Descent, a basic 
Machine Learning algorithm. These 2 factors are :
- time of day uploaded
- clicks the page has received (in any length of time)
All 6 factors are randomly generated to simulate values for balancing the 30 URLs in the tree, and also to generate a training set
for the Gradient Descent algorithm to attempt to find a relationship between the time of day uploaded, generated as a double between
0 and 24, and the number of clicks the page has gotten, a double between 1 and 1000.

Once the 30 URLs have been assigned random scores for each of its factors, a total score is calculated for the URL as an average
of the 4 main scores, then the 30 URLs are created into a Binary Search Tree that can be manipulated at will by the user. The full
details are in the documentation supplied in the repository.
