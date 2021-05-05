FROM ubuntu:20.04

RUN apt-get update \
    && apt-get install -y apt-utils sudo wget -f \
    && echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections \
    && apt-get upgrade -y \
    && apt-get install -y sudo wget -f \
    && apt-get dist-upgrade -y
RUN useradd -m user \
    && usermod -aG sudo user \
    && passwd -d user
USER user

WORKDIR /home/user

# Java 8
RUN sudo apt-get install -y openjdk-8-jdk \
    && sudo apt-get install -y curl

# Scala 2.12.13
RUN wget https://downloads.lightbend.com/scala/2.12.13/scala-2.12.13.deb \
    && sudo dpkg -i ./scala-2.12.13.deb \
    && rm scala-2.12.13.deb

# Node.js 16 and npm 7, update npm to the latest version
RUN curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash - \
    && sudo apt-get install -y nodejs \
    && sudo npm update -g \
    && sudo npm install -g npm@latest \
    && sudo npm install -g npm-check-updates

# sbt
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list \
    && echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list \
    && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add \
    && sudo apt-get update \
    && sudo apt-get install -y sbt

VOLUME [ "data" ]

EXPOSE 3000
EXPOSE 9000
