Readme.txt
1) kubernetes are used for automatic deployments of microservices to various instances without any manual intervention.
2) kubernetes are also used for rollback of new microservices in case of any failures.
3) in case of dynamic autoscaling of microservices due to heavy load it can be achieved using kubernetes.
4) in case of any issues with instance(s) due to network or any other reason then kubernets can autoheal by bringing a new instance and ensure it's availability

kubernetes Archictecture:
1) kubernetes cluster is composed of master node and worker node. Master node is responsible of managing the worker nodes 

Master nodes functionality:
( in terms of new container orchestration to pods, number of instances the microservices should have based on the configuration set up, monitoring the pods status, self healing when any instances goes down.
Master node components:
1) kube API Server. it's kind of restful webservices which is exposed to users( UI or Kubectl (command line client)) where user will give instructions to masternode he wants to deploy the microservices instances to POD, number of instances , geographical distribution of this instances.
2) Scheduler -> the scheduler is responsible for the new microservices instances to the POD in the worker node. it goes thru the various instructions requested by the user from the etcd component (kind of database). the scheduler identifies which server has less load for the particular geography and it interacts with the kubelet component in worker node to create the container inside the pods.
3) Control manager -> the control manager is the one which monitors the PODS / containers status. in case of PODS / containers is not working then it compares the number of desired instances against the number of current instances. if there is any mismatch it will self heal it.
4) the etcd component act as a database (or store's information) what user has requested for configuration(micro services name, number of instances, geographical distribution)

Worker nodes:
1) the worker nodes is the one which actually contains the code or business logic which works on the actual data. it has the database for the microservices. it usually a heavy weight compare to manager node.
2) kubelet -> this component acts as an interface for interaction with manager node. (scheduler interacts with the kubelet for microservices container orchestration.
3) docker -> as we are building and running the docker images we need the docker to installed in the server.
4) kube proxy -> the orchestrated container microservices is exposed to enduser via the kube proxy.
