WebLabora
=========

[![Circle CI](https://circleci.com/gh/philihp/weblabora/tree/master.svg?style=svg&circle-token=c9dd94399c8ddc6734ec6eae717e057b8f5b4c0e)](https://circleci.com/gh/philihp/weblabora/tree/master)

Website based Ora et Labora board game online engine. Built on Circle.

To install current snapshot locally, do this:

`mvn install`

To deploy a snapshot, do this:

`mvn deploy`

To release a new non-snapshot version, do this:

`mvn release:clean release:prepare`

follow the onscreen instructions, and then

`mvn release:perform`
