#read the reviews.csv file into the "tmd" data frame in R environment
tmd <- read.csv("reviews.csv")

#Create new data frames to store the Postive Reviews, Negative Reviews, Training dataset and Testing dataset
pr <- data.frame(Class=numeric(0), Positive.Reviews=numeric(0),Rating=numeric(0)) #Creating the data frame to store the Positive Reviews
nr <- data.frame(Class=numeric(0), Negative.Reviews=numeric(0), Rating=numeric(0)) #Creating the data frame to store the Negative Reviews
training <- data.frame(Class=numeric(0), Ratings=numeric(0), Reviews=numeric(0)) #Creating the data frame to store the Traning dataset
testing <- data.frame(Class=numeric(0), Ratings=numeric(0), Reviews=numeric(0)) #Creating the data frame to store the Testing dataset

#Intializing the counter variables to 1
k <- 1
l <- 1

#Running the for loop
for (i in 1:10261)
{
  #Checking for reviews that have ratings of 4 or 5 (Positive Reviews) 
  if(tmd$Ratings[i]=="5"|tmd$Ratings[i]=="4")
  {
    #Fetching the review and the corresponding rating from the tmd data frame and storing it into the pr data frame (Positive Reviews)
	pr[k,3] <- c(toString(tmd$Ratings[i]))
    pr[k,2] <- c(toString(tmd$Reviews[i]))
    pr[k,1] <- c(1) #Setting the class label to 1
    k <- k+1 #Updating the counter value
  }
  #Checking for reviews that have ratings of 3 or 2 or 1 (Negative Reviews)
  else if(tmd$Ratings[i]=="3"|tmd$Ratings[i]=="2"|tmd$Ratings[i]=="1")
  {
    #Fetching the review and the corresponding rating from the tmd data frame and storing it into the nr data frame (Negative Reviews)
	nr[l,3] <- c(toString(tmd$Ratings[i]))
    nr[l,2] <- c(toString(tmd$Reviews[i]))
    nr[l,1] <- c(0) #Setting the class label to 0
    l <- l+1 #Updating the counter value
  }
}

#for loop to partiton the dataset into Training and Testing
#Training dataset accounts for 80% of the total data
for(i in 1:8209)
{
  if(tmd$Ratings[i]=="5"|tmd$Ratings[i]=="4")
  {
    #Storing the positive reviews and the corresponding ratings into the training data frame
	training[i,1] <- c(1)
    training[i,2] <- c(toString(tmd$Ratings[i]))
    training[i,3] <- c(toString(tmd$Reviews[i]))
  }
  else if(tmd$Ratings[i]=="3"|tmd$Ratings[i]=="2"|tmd$Ratings[i]=="1")
  {
    #Storing the negative reviews and the corresponding ratings into the training data frame
	training[i,1] <- c(0)
    training[i,2] <- c(toString(tmd$Ratings[i]))
    training[i,3] <- c(toString(tmd$Reviews[i]))
  }
}

z <- 1 #Counter variable
#Testing dataset accounts for 20% of the total data
for(i in 8210:10261)
{
  if(tmd$Ratings[i]=="5"|tmd$Ratings[i]=="4")
  {
    #Storing the positive reviews and the corresponding ratings into the testing data frame
	testing[z,1] <- c(1)
    testing[z,2] <- c(toString(tmd$Ratings[i]))
    testing[z,3] <- c(toString(tmd$Reviews[i]))
    z <- z+1
  }
  else if(tmd$Ratings[i]=="3"|tmd$Ratings[i]=="2"|tmd$Ratings[i]=="1")
  {
    #Storing the negative reviews and the corresponding ratings into the testing data frame
	testing[z,1] <- c(0)
    testing[z,2] <- c(toString(tmd$Ratings[i]))
    testing[z,3] <- c(toString(tmd$Reviews[i]))
    z <- z+1
  }
  
}
#Viewing the different data frames
View(training) #Viewing the training data frame
View(testing) #Viewing the testing data frame
View(pr) #Viewing the positive reviews data frame
View(nr) #Viewing the negative reviews data frame

#Writing the data frames to csv files
library(foreign)
write.csv(training, "D:/Lehigh/SEM 2/Text Mining/train/TrainingDataset.csv" )
write.csv(testing, "D:/Lehigh/SEM 2/Text Mining/train/TestingDataset.csv" )
write.csv(pr, "D:/Lehigh/SEM 2/Text Mining/train/PositiveReviews.csv" )
write.csv(nr, "D:/Lehigh/SEM 2/Text Mining/train/NegativeReviews.csv" )

