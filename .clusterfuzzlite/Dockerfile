FROM gcr.io/oss-fuzz-base/base-builder-jvm:v1
RUN apt-get update && apt-get install -y make autoconf automake libtool
COPY . $SRC/javulna
WORKDIR javulna
COPY .clusterfuzzlite/build.sh $SRC/
