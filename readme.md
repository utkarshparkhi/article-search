#Article Search
###Setting up
1. modify `./sample.yml` if needed
    ```yaml
    defaultName: Search
    server:
    applicationConnectors:
      - type: http
        port: 50000
    adminConnectors:
      - type: http
        port: 50001

    ```
2. add db config in `src/config.properties`
    ```properties
    host=HOST_NAME
    port=PORT
    db_name=DB_NAME
    col_name=COLLECTION_NAME
    ```

3. make sure mongodb has text index run this in mongo console to recreate my index.<br>
    `db.COLLECTION_NAME.createIndex({text:"text",suma:"text",queries:"text"},{weights:{suma:5,queries:10}})`
   
#Start The server
`java -jar target/article-search-1.0-SNAPSHOT.jar server sample.yml`