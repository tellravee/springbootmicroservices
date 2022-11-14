In this project we have used paketobuildpacks to create docker build images.

1) the below dependency to be added in pom.xml
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<image>
						<name>tellravee/${project.artifactId}</name>
					</image>
				</configuration>
			</plugin>
			
			note: image name tag is important. it should be unique.
2)command to create docker image is
  mvn spring-boot:build-image

3) docker push docker.io/<tagname>

4) if at all if u see any error like . this will avoid docker (-access-to-resource-denied-for-docker-push)

follow the below steps

The creator of the tutorial forgot to mention we have to tag the image first 
with docker tag {image ID number} yourhubusername/{name_of_your_choice]:latest where :latest is a 
tag (I'll post photos below).

ex: -docker tag 3b6094476f79 tellravee/cards

Here is the terminal output from the above tagging and then docker push
ex:-
 docker push tellravee/cards:latest