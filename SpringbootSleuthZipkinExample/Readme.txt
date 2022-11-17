Section information:

Spring Sleuth
 Spring sleuth is used for adding distributed tracing to the application.
 it adds trace id/correlation id or span id   to the application logs. this trace id is used to identify the entire transaction and span id is used to identify the
 application here the exception has occured or monitor the request where the performance issue is happening.
 
 Application name in the logs: spring sleuth fetches the application name from the application.properties (spring.application.name)
 Trace id: it's an unique id to identify the entire transaction. it helps us to identify the entire transaction.
 Span id: Each participating service will have it's own span id. 
						it's an unique id for each application for the transaction. it will help us to identify the application error or perfomance issue.
  note: ex:- the sleuth generates the logs account.0baeeeed9.0baeeeed9 -> cards.0baeeeed9.C34567789 -> loans.0baeeeed9.D4567890
  the first microservice which receives the request the trace id and span id will be same. that one thing we have to check always.
  
 Spring zipkin
 spring zipkin is used to aggregate the logs across different application in a centralized place. we can view all the logs in a single place. we can index and 
 search the logs. it's a data visualization place where it splits the logs into components wise which will help us to identify the transaction bottle neck or performance hot spots.
 
 synchronous log messages use zipkin
 in the application.properties add zipkin endpoint details and transfer rate
 
To start  Rabbit mq: (this is used for Asynchronous log message centralization)
 Docker
 docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

to start zipkin docker image (no need of configuring zipkin. just run the docker images)
docker run -d -p 9411:9411 openzipkin/zipkin
