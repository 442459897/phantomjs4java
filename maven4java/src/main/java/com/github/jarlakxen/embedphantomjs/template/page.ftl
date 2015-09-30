<?xml version="1.0" encoding="UTF-8"?>
<people  xmlns:h="http://www.w3.org/TR/html4/">
    <person id="000001" age="20">
        <name>
            <family>${p.fname}</family>
            <given>${p.gname}</given>
        </name>
        <age>${p.age?c}</age>
        <email>${p.email}</email>
        <link manager="${p.manager}" />     
    </person>    
</people>