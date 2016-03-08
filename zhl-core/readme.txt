代码生成前，需要配置一下jdbc，并把mysql-connector-java-5.1.27.jar放到的d:\下

代码生成命令
mvn mybatis-generator:generate

建表时，字段名称建议用"_"分隔多个单词，比如:user_id...，这样生成的entity，属性名称就会变成漂亮的驼峰命名，即: userId
每个字段需要设置缺省值，可以避免无数的null判断。

表和字段，统一都用小写，容易阅读。

