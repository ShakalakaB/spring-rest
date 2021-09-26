# Spring Boot Microservices
+ ELK
    + Logstash
        + location: /media/java-projects/elk/logstash-7.14.1
        + cmd to run: `/bin/logstash`
        + log config: /media/java-projects/elk/logstash-7.14.1/config/pipelines.yml
    + Elasticsearch
        + location: /media/java-projects/elk/elasticsearch-7.14.1
        + cmd to run: `bin/elasticsearch`
        + default port: http://localhost:9200
        + list of endpoints: http://localhost:9200/_cat
        + list of index: http://localhost:9200/_cat/indices
        + search syntax: `http://localhost:9200/{index name}/_search?q=*`, or `http://localhost:9200/{index name}/_search?q=message:eureka`, pretty format: `http://localhost:9200/{index name}/_search?q=*&format&pretty`
    + Kibana
        + location: /media/java-projects/elk/kibana-7.15.0-linux-x86_64
        + cmd to run: `bin/kibana`
        + default port: http://localhost:5601

