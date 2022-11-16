Docker Instructions:

1) to Create Docker Images we can use the docker file or paketobuilds. the docker file we have to specify the 
instructions like what we need like java, any other runtime related specific environment.
   Refer: Accounts project Docker file
   
2) to build docker image
docker build . -t tellravee/accounts
  the tag (tellravee/accounts) should be unique. this will avoid docker (-access-to-resource-denied-for-docker-push)
  
3) Docker instructions -> Refer DockerInstructions.txt

4)

In this project we have used paketobuildpacks to create docker build images.

dependency pom.xml
<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<image>
						<name>eazybytes/${project.artifactId}</name>
					</image>
				</configuration>
			</plugin>
			
command to create docker image is
1)  mvn spring-boot:build-image

The creator of the tutorial forgot to mention we have to tag the image first 
with docker tag {image ID number} yourhubusername/{name_of_your_choice]:firsttry where :firsttry is a 
tag (I'll post photos below).

ex: -docker tag 3b6094476f79 tellravee/loans:firsttry

Here is the terminal output from the above tagging and then docker push
ex:-
 docker push tellravee/cards:firsttry