# geographical-api
### Instalación 🔧

```
docker build . -t geographical-api:lastest
docker-compose up
```

## API Endpoint ⚙️

- **<code>GET</code>** /api/node?longitude=&latitude=

> Response

```json[
    [
      {
          "id": 10,
          "latitude": -1.293,
          "longitude": 1.213,
          "address": "Av San River",
          "schedule": "L a V 10:00 - 18:00",
          "capacity": null,
          "type": "WITHDRAWAL_POINT"
      }
    ]
```

- **<code>POST</code>** /api/node

> Resquest

```json[
    {
        "type":"BRANCH_OFFICE",
        "address":"Av San Cayentano",
        "schedule":"L a V 10:00 - 18:00",
        "latitude":"-28.293",
        "longitude":"92.213"
    }
```

> Response 

```json[
    {
        "type":"BRANCH_OFFICE",
        "address":"Av San Cayentano",
        "schedule":"L a V 10:00 - 18:00",
        "latitude":"-28.293",
        "longitude":"92.213"
    }
```

- **<code>GET</code>** /api/node/10

> Response

```json[
    {
        "id": 9,
        "latitude": 66.0,
        "longitude": -141.0,
        "address": null,
        "schedule": null,
        "capacity": 250
    }
```

- **<code>PUT</code>** /api/node/10

> Request 

```json[
    {
        "type":"BRANCH_OFFICE",
        "address":"Av San River",
        "schedule":"L a V 10:00 - 18:00",
        "latitude":"-1.293",
        "longitude":"1.213"
    }
```

> Response 

```json[
    {
        "type":"BRANCH_OFFICE",
        "address":"Av San River",
        "schedule":"L a V 10:00 - 18:00",
        "latitude":"-1.293",
        "longitude":"1.213"
    }
```


- **<code>DELETE</code>** /api/node/10

> Response

```json[
    {
        "id": 10,
        "latitude": -28.293,
        "longitude": 92.213,
        "address": "Av San Cayentano",
        "schedule": "L a V 10:00 - 18:00",
        "capacity": null
    }
```
## Construido con 🛠️

_Herramientas utilizadas_

* [Spring boot](https://spring.io/guides) - Framework web
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Docker](https://www.docker.com/) - Docker
* [PostgreSQL](https://www.postgresql.org/) - PostgreSQL
* [PostGIS](https://postgis.net/) - PostGIS soporte posiciones greograficas.
