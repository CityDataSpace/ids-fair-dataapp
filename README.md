# IDS FAIR data app
**Contact:** FIT FAIR Team 

This is a smart IDS data app.
This app provides the functionality of ‘[FAIR evaluator service](https://gitlab.fit.fraunhofer.de/abu.ibne.bayazid/fairevaluator)‘ for evaluating metadata FAIRness of offered resources. 

It provides an HTTP API for the following endpoints:
- Data Input
- Data Output
- Configuration / Parameterization
- Processing
- Status Information
- Usage Control

## Getting started

At first clone the repository: https://gitlab.fit.fraunhofer.de/abu.ibne.bayazid/ids-fair-dataapp.git 
   
## Communication Endpoints
   
### App Endpoints 

The app endpoints for ```data-input, data-output, processing, configuration, status and usage control``` 

<table width="100%">
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><a href="http://hostname:8080/status">http://hostname:8080/status</a></td>
            <td>App enpoint for status information (e.g. lifecycle, heartbeat)</td>
        </tr>
        <tr>
            <td><a href="http://hostname:8080/input">http://hostname:8080/input</a></td>
            <td>App endpoint for consuming data to process</td>
        </tr>
        <tr>
            <td><a href="http://hostname:8080/output">http://hostname:8080/output</a></td>
            <td>App endpoint for providing processed data</td>
        </tr>
        <tr>
            <td><a href="http://hostname:8080/config">http://hostname:8080/config</a></td>
            <td>App endpoint for configuration / parameterization</td>
        </tr>
        <tr>
            <td><a href="http://hostname:8080/process">http://hostname:8080/process</a></td>
            <td>App endpoint to start the processing</td>
        </tr>
        <tr>
            <td><a href="http://hostname:8080/usage">http://hostname:8080/usage</a></td>
            <td>App endpoint for Usage Control limitations</td>
        </tr>
    </tbody>
</table>

### IDS Endpoints

The IDS endpoints within this example are not necessary, they should give an example how to create and work with the IDS App metadata description, which is an ids information model representation.
For demonstration purpose, the ```ids/encode``` endpoint delivers the base64 encoded app representation. 

<table width="100%">
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><a href="http://hostname:8080/ids">http://localhost:8080/ids</a></td>
            <td>Endpoint that provides the app's ids information model representation</td>
        </tr>
        <tr>
            <td><a href="http://hostname:8080/ids/encode">http://localhost:8080/ids/encode</a></td>
            <td>Endpoint that provides the app's ids information model representation encoded as base64</td>
        </tr>
    </tbody>
</table>

### Documentation Endpoint

This endpoint is also mapped to the root path of the app template. It provides a swagger UI / openapi overview and documentation for all the other endpoints. 

<table width="100%">
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><a href="http://localhost:8080/ids/encode">http://localhost:8080/</a></td>
            <td>Swagger UI documentation for the example app</td>
        </tr>
    </tbody>
</table>

## IDS App Representation

An important part of the IDS App ecosystem is the app metadata representation according to the [IDS information model](https://github.com/International-Data-Spaces-Association/InformationModel).
In the following section the different aspects of the IDS information model representation will be explained in detail.
The app metadata inside the IDS ecosystem are exchnaged in a JSON-LD format. 
This app template uses a [Java library from Fraunhofer IAIS](https://maven.iais.fraunhofer.de/ui/native/eis-ids-public/de/fraunhofer/iais/eis/ids/infomodel/java/) for the generation and serialization of the app metadata and the corresponding JSON-LD representation. 
A full version of the metadata representation can be accessed via the ```/ids``` endpoint. In addition, a full JSON-LD and Turtle version are located in the project ```resource``` folder.

### General app information fields
- title             > The app title
- version           > The application release version number
- description       > The application short description
- keyword           > Keywords that are also interpreted as app categories by the appstore  
- created           > Creation date    
- modified          > Modification date
- customLicense     > URI to custom license information
- standardLicense   > URI to standard license information

``` json
"ids:title": [
    {
      "@value": "IDSSmartDataAppTemplate",
      "@language": "https://w3id.org/idsa/code/EN"
    }
],
"ids:version": "1.0.0",
"ids:description": [
    {
      "@value": "Example Smart-Data-App for demonstration purposes",
      "@language": "https://w3id.org/idsa/code/EN"
    }
  ],
"ids:keyword": [
    {
      "@value": "Demo",
      "@language": "https://w3id.org/idsa/code/EN"
    },
    {
      "@value": "Example",
      "@language": "https://w3id.org/idsa/code/EN"
    },
    {
      "@value": "Smart-Data-App",
      "@language": "https://w3id.org/idsa/code/EN"
    }
  ],
"ids:created": "2020-11-11T17:42:50.609UTC",
"ids:modified": "2020-11-11T17:42:50.612UTC",
"ids:standardLicense": "https://www.apache.org/licenses/LICENSE-2.0",
"ids:customLicense": "https://www.apache.org/licenses/LICENSE-2.0"
```
           
### Representation
An IDS data app consists at least of one app representation which in the first place describes the runtime environment.
If the app is a virtualization container the AppRuntimeEnvironment should be used to set the virtualization environment here (docker, kn8s)
In addition to this, the app distributionService could be set. This field indicates where the related container is stored.
(e.g. if your are using private container registry) 

- dataAppRuntimeEnvironment     > The application runtime environment
- dataAppDistributionService    > The appstore url or the registry where the app is located

``` json
"ids:dataAppRuntimeEnvironment": "Docker",
"ids:dataAppDistributionService": "https://appstore.fit.fraunhofer.de/registry"
```

## Data App Information
In this section settings related to the app deployment and the app documentation can be defined here. 
If the app needs some environment variables or volume mountings for execution, these can be set within the following fields.

- appDocumentation          > The application documentation in human readable text 
- appEnvironmentVariables   > Environment variables needed by the app (configuration or settings)
- appStorageConfiguration   > Volumes to mount for the application execution

``` json
 "ids:appDocumentation": "Place for an app-related human-readable documentation",
 "ids:appEnvironmentVariables": "dbUser=sa;dbPasswd=passwd",
 "ids:appStorageConfiguration": "-v /data"
```

### AppEndpoint
- path                      > The path where the app endpoint is mapped to (in this template the path relates to the spring boot rest controller mapping)
- appEndpointProtocol       > The protocol the endpoint is listening on
- endpointInformation       > Information about the endpoint in human readable text
- endpointDocumentation     > Uri to an external endpoint documentation
- appEndpointMediaType      > IANAMediatype an endpoint sends or receives
- appEndpointPort           > The port number the endpoint listens for connections
- appEndpointType           > Descripes wether the endpoint is an ```INPUT, OUTPUT, CONFIG, PROCESS, STATUS, USAGE_POLICY``` endpoint

``` json
"ids:path": "/input",
"ids:appEndpointProtocol": "HTTP/1.1",
"ids:endpointInformation": [
    {
        "@value": "Endpoint for app input data",
        "@language": "https://w3id.org/idsa/code/EN"
    }
],
"ids:endpointDocumentation": [
    "https://app.swaggerhub.com/apis/app/1337"
],
"ids:appEndpointMediaType": {
    "@type": "ids:IANAMediaType",
    "@id": "https://w3id.org/idsa/autogen/iANAMediaType/27f8fdd6-d214-46c0-82c2-46d56f5ac464",
    "ids:filenameExtension": "application/json"
},
"ids:appEndpointPort": 8080,
"ids:appEndpointType": {
    "@id": "idsc:INPUT_ENDPOINT"
}
```

## Container generation / Docker-File

To be able to publish the data apps, they have to be packaged into a container.
In this example we use Docker container. The environment variables, port shares and volume locations required by the application for operation are specified in the docker file according to the Docker documentation.
However, it is important to make sure that the information is also specified in the app metadata, because the later deployment of an app in a connector depends on the information in the metadata.
The specification of the parameters is therefore subject to a higher priority. When deploying the app via the AppStore, an app-specific container template is provided from the metadata.

The following example of an dockerfile describes the relation between the docker-file and the app metadata representation: 

```dockerfile
# EVIRONMENT VARIABLES MAPPING FOR IDS DATA APP
# Environment variables can be used in the app metadata under the following property path 
# "ids:representation" --> "ids:appEnvironmentVariables": "$dbUser=sa;$dpPasswd=passwd",
ENV dbUser="sa"
ENV dbPasswd="passwd"

# VOLUME MAPPING FOR IDS DATA APP
# Volumes or files can be used in the app metadata under the following property path
# "ids:representation" --> "ids:appStorageConfiguration": "-v /data",
# "ids:representation" --> "ids:appStorageConfiguration": "-v /data/file.tmp",
VOLUME ["/data"]

# PORT MAPPING FOR IDS DATA APP ENDPOINTS
# Ports can be used in the app metadata under the following property path
# Each endpoint has its own port number, so multiple ports per app are possible
# Each port can be exposed in docker or mapped to a custom host port
# "ids:representation" --> "ids:appEndpoint" --> "ids:appEndpointPort": "8080"
EXPOSE 8080/tcp

# IDS APP DESCRIPTION ENCODED AS BASE64 ENCODED STRING REPRESENTATION
# The app metadata will be published as an encoded version with the help of an Docker label named "IDS-Metadata"
# In this app template, the encoded version can be found as a result of the /ids/encode endpoint
LABEL maintainer="FIT AppStore Team <ids-appstore@fit.fraunhofer.de>"
LABEL IDS-METADATA="eyJAdHlwZSI6ImlkczpBcHBSZXNvdXJjZSIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2FwcFJlc291cmNlLzU1NjYzZjNiLTJkZjYtNGRmYy1hYTdkLWM3MTI5Yjc1MjRjZSIsImlkczpsYW5ndWFnZSI6bnVsbCwiaWRzOnZhcmlhbnQiOm51bGwsImlkczp2ZXJzaW9uIjoiMS4wLjAiLCJpZHM6Y3JlYXRlZCI6IjIwMjAtMTEtMTFUMTg6MTA6NTcuNzQ4VVRDIiwiaWRzOmNvbnRlbnRUeXBlIjpudWxsLCJpZHM6ZGVzY3JpcHRpb24iOlt7IkB2YWx1ZSI6IkV4YW1wbGUgU21hcnQtRGF0YS1BcHAgZm9yIGRlbW9uc3RyYXRpb24gcHVycG9zZXMiLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XSwiaWRzOnRoZW1lIjpudWxsLCJpZHM6c2hhcGVzR3JhcGgiOm51bGwsImlkczptb2RpZmllZCI6IjIwMjAtMTEtMTFUMTg6MTA6NTcuNzUwVVRDIiwiaWRzOmFzc2V0UmVmaW5lbWVudCI6bnVsbCwiaWRzOnJlcHJlc2VudGF0aW9uIjpbeyJAdHlwZSI6ImlkczpBcHBSZXByZXNlbnRhdGlvbiIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2FwcFJlcHJlc2VudGF0aW9uL2JlMmFkYjJmLTJhYWEtNDU5OS1iYWY5LTIzOWRmMTY2YWQwOCIsImlkczppbnN0YW5jZSI6bnVsbCwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6Y3JlYXRlZCI6bnVsbCwiaWRzOm1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS80MjMzZTdmYy00OGZlLTQ2NTMtOGY1Yi05ZmU2MzZkMGRjNmEiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi96aXAifSwiaWRzOnNoYXBlc0dyYXBoIjpudWxsLCJpZHM6bW9kaWZpZWQiOm51bGwsImlkczpkYXRhQXBwUnVudGltZUVudmlyb25tZW50IjoiRG9ja2VyIiwiaWRzOnJlcHJlc2VudGF0aW9uU3RhbmRhcmQiOm51bGwsImlkczpkYXRhQXBwRGlzdHJpYnV0aW9uU2VydmljZSI6Imh0dHBzOi8vYXBwc3RvcmUuZml0LmZyYXVuaG9mZXIuZGUvcmVnaXN0cnkiLCJpZHM6ZGF0YUFwcEluZm9ybWF0aW9uIjp7IkB0eXBlIjoiaWRzOlNtYXJ0RGF0YUFwcCIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL3NtYXJ0RGF0YUFwcC9jNTZiNGRhYi02MjcyLTRjOWQtOWJjYy1jNzQzZmM3MmM1MDYiLCJpZHM6YXBwRW5kcG9pbnQiOlt7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvYzkwMmVmODMtMGM4Zi00NDM3LWFjMmQtOGU2MTgxNTIzMzUwIiwiaWRzOnBhdGgiOiIvaW5wdXQiLCJpZHM6bGFuZ3VhZ2UiOm51bGwsImlkczphY2Nlc3NVUkwiOm51bGwsImlkczphcHBFbmRwb2ludFBvcnQiOjgwODAsImlkczphcHBFbmRwb2ludFR5cGUiOnsicHJvcGVydGllcyI6bnVsbCwiQGlkIjoiaWRzYzpJTlBVVF9FTkRQT0lOVCJ9LCJpZHM6aW5ib3VuZFBhdGgiOm51bGwsImlkczpvdXRib3VuZFBhdGgiOm51bGwsImlkczplbmRwb2ludERvY3VtZW50YXRpb24iOlsiaHR0cHM6Ly9hcHAuc3dhZ2dlcmh1Yi5jb20vYXBpcy9hcHAvMTMzNyJdLCJpZHM6YXBwRW5kcG9pbnRNZWRpYVR5cGUiOnsiQHR5cGUiOiJpZHM6SUFOQU1lZGlhVHlwZSIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2lBTkFNZWRpYVR5cGUvNGRlNzBjMDktMjgxNC00YzljLWI5OWMtZTI5MGJjNGY1ODZjIiwiaWRzOmZpbGVuYW1lRXh0ZW5zaW9uIjoiYXBwbGljYXRpb24vanNvbiJ9LCJpZHM6YXBwRW5kcG9pbnRQcm90b2NvbCI6IkhUVFAvMS4xIiwiaWRzOmVuZHBvaW50SW5mb3JtYXRpb24iOlt7IkB2YWx1ZSI6IkVuZHBvaW50IGZvciBhcHAgaW5wdXQgZGF0YSIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn1dfSx7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvZWMyODc2YmUtYTBiMi00ZWI5LWFiMTEtNjk5NDc2ODNmNjg5IiwiaWRzOnBhdGgiOiIvb3V0cHV0IiwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6YWNjZXNzVVJMIjpudWxsLCJpZHM6YXBwRW5kcG9pbnRQb3J0Ijo4MDgwLCJpZHM6YXBwRW5kcG9pbnRUeXBlIjp7InByb3BlcnRpZXMiOm51bGwsIkBpZCI6Imlkc2M6T1VUUFVUX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS9lYzI3YWYwZC0xNTRkLTQ0YWYtYWE1ZC03OWEyMTkwMGRhMTUiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBvdXRwdXQgZGF0YSIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn1dfSx7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvOTA2ZTdkODUtZDg4YS00MTNiLWJkYmEtMjExMjNmOWQ5OWJkIiwiaWRzOnBhdGgiOiIvY29uZmlnIiwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6YWNjZXNzVVJMIjpudWxsLCJpZHM6YXBwRW5kcG9pbnRQb3J0Ijo4MDgwLCJpZHM6YXBwRW5kcG9pbnRUeXBlIjp7InByb3BlcnRpZXMiOm51bGwsIkBpZCI6Imlkc2M6Q09ORklHX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS83OGRiOWMyNC0wZGNlLTRmMWUtODQxOC0xZjFhY2UwNzc1YmIiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBjb25maWd1cmF0aW9uIC8gcGFyYW1ldGVyaXphdGlvbiIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn1dfSx7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvZGJmYmZjNWYtYWExNy00ODUzLTlkZGEtNzBlOTVmNWQ5ODA5IiwiaWRzOnBhdGgiOiIvc3RhdHVzIiwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6YWNjZXNzVVJMIjpudWxsLCJpZHM6YXBwRW5kcG9pbnRQb3J0Ijo4MDgwLCJpZHM6YXBwRW5kcG9pbnRUeXBlIjp7InByb3BlcnRpZXMiOm51bGwsIkBpZCI6Imlkc2M6U1RBVFVTX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS82MTYwMzQ5MC1lZmM4LTQyMTAtYmMwZC04N2M4OTM3YTUzNGMiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBzdGF0dXMgaW5mb3JtYXRpb24iLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XX0seyJAdHlwZSI6ImlkczpBcHBFbmRwb2ludCIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2FwcEVuZHBvaW50LzBkYzk1M2RhLTBkZmQtNGM5NS1hMmMxLTIzNTg5OWFlOGUxNCIsImlkczpwYXRoIjoiL3Byb2Nlc3MiLCJpZHM6bGFuZ3VhZ2UiOm51bGwsImlkczphY2Nlc3NVUkwiOm51bGwsImlkczphcHBFbmRwb2ludFBvcnQiOjgwODAsImlkczphcHBFbmRwb2ludFR5cGUiOnsicHJvcGVydGllcyI6bnVsbCwiQGlkIjoiaWRzYzpQUk9DRVNTX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS80MzU5ZTdmZi1lYWVlLTQyNTctYTc5MC1mOGM4N2QwNzVhYTgiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBwcm9jZXNzaW5nIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifV19LHsiQHR5cGUiOiJpZHM6QXBwRW5kcG9pbnQiLCJAaWQiOiJodHRwczovL3czaWQub3JnL2lkc2EvYXV0b2dlbi9hcHBFbmRwb2ludC9jOGE5ZTIyZC03MWQ3LTQzZWItYWQ0YS1hOGM2YmZhYjAxZTUiLCJpZHM6cGF0aCI6Ii91c2FnZSIsImlkczpsYW5ndWFnZSI6bnVsbCwiaWRzOmFjY2Vzc1VSTCI6bnVsbCwiaWRzOmFwcEVuZHBvaW50UG9ydCI6ODA4MCwiaWRzOmFwcEVuZHBvaW50VHlwZSI6eyJwcm9wZXJ0aWVzIjpudWxsLCJAaWQiOiJpZHNjOlVTQUdFX1BPTElDWV9FTkRQT0lOVCJ9LCJpZHM6aW5ib3VuZFBhdGgiOm51bGwsImlkczpvdXRib3VuZFBhdGgiOm51bGwsImlkczplbmRwb2ludERvY3VtZW50YXRpb24iOlsiaHR0cHM6Ly9hcHAuc3dhZ2dlcmh1Yi5jb20vYXBpcy9hcHAvMTMzNyJdLCJpZHM6YXBwRW5kcG9pbnRNZWRpYVR5cGUiOnsiQHR5cGUiOiJpZHM6SUFOQU1lZGlhVHlwZSIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2lBTkFNZWRpYVR5cGUvYzMxYzBiMDgtZTk5ZC00ZmM3LWE2N2ItMzJlOTkwMTVjOGRiIiwiaWRzOmZpbGVuYW1lRXh0ZW5zaW9uIjoiYXBwbGljYXRpb24vanNvbiJ9LCJpZHM6YXBwRW5kcG9pbnRQcm90b2NvbCI6IkhUVFAvMS4xIiwiaWRzOmVuZHBvaW50SW5mb3JtYXRpb24iOlt7IkB2YWx1ZSI6IkVuZHBvaW50IGZvciBhcHAgdXNhZ2UgcG9saWNpZXMiLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XX1dLCJpZHM6YXBwRW52aXJvbm1lbnRWYXJpYWJsZXMiOiIkZGJVc2VyPXNhOyRkcFBhc3N3ZD1wYXNzd2QiLCJpZHM6YXBwRG9jdW1lbnRhdGlvbiI6IlBsYWNlIGZvciBhbiBhcHAtcmVsYXRlZCBodW1hbi1yZWFkYWJsZSBkb2N1bWVudGF0aW9uIiwiaWRzOmFwcFN0b3JhZ2VDb25maWd1cmF0aW9uIjoiLXYgL2RhdGEifX1dLCJpZHM6Y29udGVudFN0YW5kYXJkIjpudWxsLCJpZHM6a2V5d29yZCI6W3siQHZhbHVlIjoiRGVtbyIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn0seyJAdmFsdWUiOiJFeGFtcGxlIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifSx7IkB2YWx1ZSI6IlNtYXJ0LURhdGEtQXBwIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifV0sImlkczpzYW1wbGUiOm51bGwsImlkczpzb3ZlcmVpZ24iOm51bGwsImlkczpyZXNvdXJjZVBhcnQiOm51bGwsImlkczpzcGF0aWFsQ292ZXJhZ2UiOm51bGwsImlkczpjb250ZW50UGFydCI6bnVsbCwiaWRzOmNvbnRyYWN0T2ZmZXIiOm51bGwsImlkczpwdWJsaXNoZXIiOm51bGwsImlkczpzdGFuZGFyZExpY2Vuc2UiOiJodHRwczovL3d3dy5hcGFjaGUub3JnL2xpY2Vuc2VzL0xJQ0VOU0UtMi4wIiwiaWRzOmN1c3RvbUxpY2Vuc2UiOiJodHRwczovL3d3dy5hcGFjaGUub3JnL2xpY2Vuc2VzL0xJQ0VOU0UtMi4wIiwiaWRzOmRlZmF1bHRSZXByZXNlbnRhdGlvbiI6bnVsbCwiaWRzOnRlbXBvcmFsUmVzb2x1dGlvbiI6bnVsbCwiaWRzOnRlbXBvcmFsQ292ZXJhZ2UiOm51bGwsImlkczpyZXNvdXJjZUVuZHBvaW50IjpbeyJAdHlwZSI6ImlkczpDb25uZWN0b3JFbmRwb2ludCIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2Nvbm5lY3RvckVuZHBvaW50L2ZiNTk5NDY2LTVmNTQtNGRmMS1iNzUzLWZkZDM0ODQ5ODhjNSIsImlkczpwYXRoIjoiLyIsImlkczphY2Nlc3NVUkwiOiJodHRwczovL2FwcHN0b3JlLmZpdC5mcmF1bmhvZmVyLmRlIiwiaWRzOmluYm91bmRQYXRoIjpudWxsLCJpZHM6b3V0Ym91bmRQYXRoIjpudWxsLCJpZHM6ZW5kcG9pbnREb2N1bWVudGF0aW9uIjpbImh0dHA6Ly9hcHBzdG9yZS5maXQuZnJhdW5ob2Zlci5kZS9hcHAvYXBwSWQiXSwiaWRzOmVuZHBvaW50SW5mb3JtYXRpb24iOlt7IkB2YWx1ZSI6IlRoaXMgaXMgdGhlIGRlZmF1bHQgZW5kcG9pbnQgb2YgdGhlIEZyYXVuaG9mZXIgRklUIEFwcFN0b3JlIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifV0sImlkczplbmRwb2ludEFydGlmYWN0IjpudWxsfV0sImlkczphY2NydWFsUGVyaW9kaWNpdHkiOm51bGwsImlkczp0aXRsZSI6W3siQHZhbHVlIjoiSURTU21hcnREYXRhQXBwVGVtcGxhdGUiLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XX0="
```
