FROM gcr.io/oss-fuzz-base/base-builder-jvm       # Base image with jvm toolchain

LABEL maintainer=Thomas

RUN apt-get update && apt-get install -y maven   # Install required packages to build your project.

RUN git clone https://github.com/l1gh7w34ver/javulna

COPY . $SRC/javulna                      # Copy your projects source code.

WORKDIR $SRC/javulna                     # Working directory for build.sh.

COPY ./.clusterfuzzlite/build.sh $SRC/           # Copy build.sh into $SRC dir.
#RUN mvn clean install

#RUN java -jar target/javulna-1.0-SNAPSHOT.jar --server.port=8089