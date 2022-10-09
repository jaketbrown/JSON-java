FROM gcr.io/oss-fuzz-base/base-builder-jvm
RUN curl -L https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip -o maven.zip && unzip maven.zip -d $SRC/maven && rm -rf maven.zip

ENV MVN $SRC/maven/apache-maven-3.8.6/bin/mvn

RUN git clone --depth 1 https://github.com/google/fuzzing && mv fuzzing/dictionaries/json.dict $SRC/JsonJavaFuzzer.dict && rm -rf fuzzing

RUN git clone --depth 1 https://github.com/dvyukov/go-fuzz-corpus && zip -j $SRC/JsonJavaFuzzer_seed_corpus.zip go-fuzz-corpus/json/corpus/* && rm -rf go-fuzz-corpus

COPY . json-java
RUN rm -rf json-java/mayhem

COPY mayhem/build.sh $SRC/
COPY mayhem/*.java $SRC/
WORKDIR  $SRC/json-java

ENV FUZZING_LANGUAGE=jvm SANITIZER=address FUZZING_ENGINE=libfuzzer
RUN compile
RUN chmod +x /out/jazzer_driver
