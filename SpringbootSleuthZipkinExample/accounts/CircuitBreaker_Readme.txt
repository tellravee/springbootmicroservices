Circuit Breaker Pattern:
it works in the way when a short circuit happens at a house the circuit breaker will open the connection such that the current is not passed on the same line. similary when one of 
the service got broken above the threshold limit it opens so that the request is not passed to the service. it waits for a specified duration makes a half open and checks for the
threshold failure. if it's more than the threshold failure then it opens the connection and wait for specified time. 
in case of failure we can always a specify a fallback method to handle the condition.
if the service call went thru it will close the connection. 

Configuration:
1) threshold limit= 50 (50% of failures)
2) number of request to be monitored= 50( it will monitor the 50 request to consider for failures)
3) wait_duration_for_half_open=30000(it waits for 30 seconds to make circuit half open)
4) permitted-number-of-calls-in-half-open-state=20 (it monitors 20 request ..if 50% of request fails in that it will open the circuit one more time)

Advantages:
 1) fail fast (based on the threshold limit it will open the circuit)
 2) fall safe (we can always have fallback method)
 3)recovery seamlessly. (it waits for the specified duration and makes a half open of circuit)
 
 Retry:
 It's one other resilence pattern. the retry pattern will make configured retry attempts. 

 configuration:
 1) maximum retry.
 2) wait duration
 3) ignore exceptions (what are the exceptions we can ignore as failure)
 4) Failure exceptions (configure the exceptions which are to be considered as failure).
 Advantages:
 1) fall safe (we can always have a fall back
 2) it does a mutiple retry before responding in case of any failure.
 
 Rate Limiter Pattern:
 the rate limiter pattern will help to stop overloading the service with more calls than it consumed in the given time.
 this rate limiter pattern helps our service from harmful effects such as denial of service, cascading failure.
 Configuration:
 
 timeoutDuration= for the given period if we can process 100 request then the new incoming request has to wait for this timeout duration to consider for processing. 
                  even after that if it's not able to process then it returns a max request error.
				  
 limitforPeriod = the number of request which can be processed for the limitRefreshPeriod. (100 request)
 
 LimitRefreshPeriod= the period of a limit refresh. after the period if the service has a capacity to process addditional 50 request then it accepts the request.