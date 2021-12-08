# Spring Boot Data Geode - MappingException reproduced

## Steps to reproduce

In one terminal, run geode server/locator via docker:

```bash
cd docker
docker-compose up
```

In another terminal, run spring app:

```bash
./gradlew bootRun
```

See the exception as below:

```bash
2021-12-08 14:24:30.452 ERROR 63881 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.dao.DataAccessResourceFailureException: PdxSerializer failed when calling toData on class com.demo.relationalGeode.SomeObject; nested exception is org.apache.geode.ToDataException: PdxSerializer failed when calling toData on class com.demo.relationalGeode.SomeObject
        at org.springframework.data.gemfire.GemfireCacheUtils.convertGemfireAccessException(GemfireCacheUtils.java:211) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at org.springframework.data.gemfire.AbstractBasicCacheFactoryBean.translateExceptionIfPossible(AbstractBasicCacheFactoryBean.java:867) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61) ~[spring-tx-5.3.13.jar:5.3.13]
        at org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:242) ~[spring-tx-5.3.13.jar:5.3.13]
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:152) ~[spring-tx-5.3.13.jar:5.3.13]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215) ~[spring-aop-5.3.13.jar:5.3.13]
        at com.sun.proxy.$Proxy82.save(Unknown Source) ~[na:na]
        at com.demo.relationalGeode.GemfireConfig.lambda$initializeCache$1(GemfireConfig.java:36) ~[main/:na]
        at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:176) ~[spring-context-5.3.13.jar:5.3.13]
        at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:169) ~[spring-context-5.3.13.jar:5.3.13]
        at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:143) ~[spring-context-5.3.13.jar:5.3.13]
        at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:421) ~[spring-context-5.3.13.jar:5.3.13]
        at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:378) ~[spring-context-5.3.13.jar:5.3.13]
        at org.springframework.boot.context.event.EventPublishingRunListener.ready(EventPublishingRunListener.java:114) ~[spring-boot-2.6.1.jar:2.6.1]
        at org.springframework.boot.SpringApplicationRunListeners.lambda$ready$6(SpringApplicationRunListeners.java:82) ~[spring-boot-2.6.1.jar:2.6.1]
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1541) ~[na:na]
        at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:120) ~[spring-boot-2.6.1.jar:2.6.1]
        at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:114) ~[spring-boot-2.6.1.jar:2.6.1]
        at org.springframework.boot.SpringApplicationRunListeners.ready(SpringApplicationRunListeners.java:82) ~[spring-boot-2.6.1.jar:2.6.1]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:317) ~[spring-boot-2.6.1.jar:2.6.1]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) ~[spring-boot-2.6.1.jar:2.6.1]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1290) ~[spring-boot-2.6.1.jar:2.6.1]
        at com.demo.relationalGeode.RelationalGeodeApplication.main(RelationalGeodeApplication.java:10) ~[main/:na]
Caused by: org.apache.geode.ToDataException: PdxSerializer failed when calling toData on class com.demo.relationalGeode.SomeObject
        at org.apache.geode.internal.InternalDataSerializer.writePdx(InternalDataSerializer.java:2821) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.InternalDataSerializer.writeUserObject(InternalDataSerializer.java:1618) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.InternalDataSerializer.writeWellKnownObject(InternalDataSerializer.java:1517) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.InternalDataSerializer.basicWriteObject(InternalDataSerializer.java:2034) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.DataSerializer.writeObject(DataSerializer.java:2839) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.util.BlobHelper.serializeTo(BlobHelper.java:68) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.tier.sockets.Message.serializeAndAddPart(Message.java:406) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.tier.sockets.Message.addObjPart(Message.java:350) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.tier.sockets.Message.addObjPart(Message.java:329) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.cache.client.internal.PutOp$PutOpImpl.<init>(PutOp.java:244) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.cache.client.internal.PutOp$PutOpImpl.<init>(PutOp.java:177) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.cache.client.internal.PutOp.execute(PutOp.java:65) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.cache.client.internal.ServerRegionProxy.put(ServerRegionProxy.java:157) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.serverPut(LocalRegion.java:3044) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.cacheWriteBeforePut(LocalRegion.java:3159) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.ProxyRegionMap.basicPut(ProxyRegionMap.java:238) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.virtualPut(LocalRegion.java:5608) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.virtualPut(LocalRegion.java:5586) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegionDataView.putEntry(LocalRegionDataView.java:157) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.basicPut(LocalRegion.java:5044) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.validatedPut(LocalRegion.java:1645) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.LocalRegion.put(LocalRegion.java:1632) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.cache.AbstractRegion.put(AbstractRegion.java:445) ~[geode-core-1.14.0.jar:na]
        at org.springframework.data.gemfire.GemfireTemplate.put(GemfireTemplate.java:205) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at org.springframework.data.gemfire.repository.support.SimpleGemfireRepository.save(SimpleGemfireRepository.java:157) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:289) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:137) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:121) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:529) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:285) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:638) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:163) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:138) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215) ~[spring-aop-5.3.13.jar:5.3.13]
        at com.sun.proxy.$Proxy82.save(Unknown Source) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-5.3.13.jar:5.3.13]
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-5.3.13.jar:5.3.13]
        ... 19 common frames omitted
Caused by: org.springframework.data.mapping.MappingException: An error occurred while serializing entity [com.demo.relationalGeode.SomeObject] property [customFirstObjects] value [[CustomFirstObject(id=11, amount=2.0)]] of type [java.util.Arrays$ArrayList] to PDX
        at org.springframework.data.gemfire.mapping.MappingPdxSerializer.lambda$doToData$3(MappingPdxSerializer.java:655) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at org.springframework.data.mapping.model.BasicPersistentEntity.doWithProperties(BasicPersistentEntity.java:360) ~[spring-data-commons-2.6.0.jar:2.6.0]
        at org.springframework.data.gemfire.mapping.MappingPdxSerializer.doToData(MappingPdxSerializer.java:617) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at org.springframework.data.gemfire.mapping.MappingPdxSerializer.toData(MappingPdxSerializer.java:593) ~[spring-data-geode-2.6.0.jar:2.6.0]
        at org.apache.geode.internal.InternalDataSerializer.writePdx(InternalDataSerializer.java:2772) ~[geode-core-1.14.0.jar:na]
        ... 69 common frames omitted
Caused by: org.apache.geode.pdx.PdxSerializationException: Exception while serializing a PDX field
        at org.apache.geode.pdx.internal.PdxOutputStream.writeObject(PdxOutputStream.java:74) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.pdx.internal.PdxWriterImpl.writeObject(PdxWriterImpl.java:341) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.pdx.internal.PdxWriterImpl.writeField(PdxWriterImpl.java:766) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.pdx.internal.PdxWriterImpl.writeField(PdxWriterImpl.java:683) ~[geode-core-1.14.0.jar:na]
        at org.springframework.data.gemfire.mapping.MappingPdxSerializer.lambda$doToData$3(MappingPdxSerializer.java:645) ~[spring-data-geode-2.6.0.jar:2.6.0]
        ... 73 common frames omitted
Caused by: java.io.NotSerializableException: com.demo.relationalGeode.CustomFirstObject
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1185) ~[na:na]
        at java.base/java.io.ObjectOutputStream.writeArray(ObjectOutputStream.java:1379) ~[na:na]
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1175) ~[na:na]
        at java.base/java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1553) ~[na:na]
        at java.base/java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1510) ~[na:na]
        at java.base/java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1433) ~[na:na]
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1179) ~[na:na]
        at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:349) ~[na:na]
        at org.apache.geode.internal.InternalDataSerializer.writeSerializableObject(InternalDataSerializer.java:2186) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.internal.InternalDataSerializer.basicWriteObject(InternalDataSerializer.java:2055) ~[geode-core-1.14.0.jar:na]
        at org.apache.geode.pdx.internal.PdxOutputStream.writeObject(PdxOutputStream.java:72) ~[geode-core-1.14.0.jar:na]
        ... 77 common frames omitted

2021-12-08 14:24:30.473  INFO 63881 --- [           main] o.a.g.internal.cache.GemFireCacheImpl    : GemFireCache[id = 2095855989; isClosing = true; isShutDownAll = false; created = Wed Dec 08 14:24:28 EST 2021; server = false; copyOnRead = false; lockLease = 120; lockTimeout = 60]: Now closing.
2021-12-08 14:24:30.483  INFO 63881 --- [           main] o.a.g.cache.client.internal.PoolImpl     : Destroying connection pool DEFAULT

> Task :bootRun FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':bootRun'.
> Process 'command '/usr/local/Cellar/openjdk@11/11.0.9/libexec/openjdk.jdk/Contents/Home/bin/java'' finished with non-zero exit value 1
```
