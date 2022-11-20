Section 12 Spring boot Monitoring Microservices health and metrics

Microservices monitoring:
in the real world 100's of microservices will be running. to collect the metrics microservices has 
a tool called micrometer which will monitor JVM metrics, cpu usage etc. this acutally uses the actuator end points.

in pom.xml we have to add the dependency.

<dependency>
			    <groupId>io.micrometer</groupId>
			    <artifactId>micrometer-core</artifactId>
			    <version>1.10.1</version>
			</dependency>

Configuration:

MicroServices prometheus:
this tool will help in monitoring the microservices. this tool has some limitation in terms of customizing dashboard. it as predefined rulesets like system_cpu_usage etc,

pom.xml dependency:
<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
			<scope>runtime</scope>
		</dependency>

  prometheus:
   image: prom/prometheus:latest
   ports:
      - "9090:9090"
   volumes:
    - ./../Monitoring/prometheus.yml:/etc/prometheus/prometheus.yml
   networks:
    - tellravee

Microservices grafana:
this tools is used to create alert and custom dashboard. this is an advanced version of tool which uses the prometheus data to create the dashboards and alerts.
there is no dependency required. we are using the graffana docker images which is exposed on default port 3000.
grafana:
   image: "grafana/grafana:latest"
   ports:
    - "3000:3000"
   environment:
    - GF_SECURITY_ADMIN_USER=admin
    - GF_SECURITY_ADMIN_PASSWORD=admin
   networks:
    - tellravee
   depends_on:
    - prometheus
