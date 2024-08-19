# Apache Superset Workflow Demo V1

```
Author(s):      Alejandro Araya Jiménez, Jeremy quirós marín, Ricardo Artavia Solano, Sebastián Acuña Pérez
Date Created:   2024-08-17
Date Updated:   2024-08-17
License:        Apache 2.0
```

---

## Introduction

This repository assumes you have `Docker`, `Docker-compose`, `Java` and `Node` installed in your local environment.

## Superset Installation

Please check this [Quickstart Guide](https://superset.apache.org/docs/quickstart/) from Apache Superset!
> This repository already ignores `superset` clones

```shell
$ git clone https://github.com/apache/superset
$ cd superset
$ docker compose -f docker-compose-image-tag.yml up
```

> This code block works for both PowerShell and Unix-like shells 