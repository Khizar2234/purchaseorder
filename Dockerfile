From openjdk:8
copy target/InventoryServiceOrderProcessing-0.0.1-SNAPSHOT.jar InventoryServiceOrderProcessing-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","InventoryServiceOrderProcessing-0.0.1-SNAPSHOT.jar"]
EXPOSE 8093
