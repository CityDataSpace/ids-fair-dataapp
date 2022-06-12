FROM maven:latest AS maven
LABEL maintainer="abu ibne bayazid"

COPY pom.xml /tmp/

WORKDIR tmp
RUN mvn verify clean --fail-never

COPY src /tmp/src/

RUN mvn clean package -DskipTests -Dmaven.javadoc.skip=true

FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine
RUN mkdir /app

COPY --from=maven /tmp/target/*.jar /app/app.jar

WORKDIR /app/

ENTRYPOINT ["java","-jar","app.jar"]

# EVIRONMENT VARIABLES MAPPING FOR IDS DATA APP
# "ids:representation" --> "ids:appEnvironmentVariables": "$dbUser=sa;$dpPasswd=passwd",
ENV dbUser="sa"
ENV dbPasswd="passwd"

# VOLUME MAPPING FOR IDS DATA APP
# "ids:representation" --> "ids:appStorageConfiguration": "-v /data",
VOLUME ["/data"]

# PORT MAPPING FOR IDS DATA APP ENDPOINTS
# "ids:representation" --> "ids:appEndpoint" --> "ids:appEndpointPort": "8080"
EXPOSE 8080/tcp

# IDS APP DESCRIPTION ENCODED AS BASE64 ENCODED STRING REPRESENTATION
# Result from /ids/encoded endpoint
LABEL maintainer="abu ibne bayazid"
LABEL IDS-METADATA="eyJAdHlwZSI6ImlkczpBcHBSZXNvdXJjZSIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2FwcFJlc291cmNlLzU1NjYzZjNiLTJkZjYtNGRmYy1hYTdkLWM3MTI5Yjc1MjRjZSIsImlkczpsYW5ndWFnZSI6bnVsbCwiaWRzOnZhcmlhbnQiOm51bGwsImlkczp2ZXJzaW9uIjoiMS4wLjAiLCJpZHM6Y3JlYXRlZCI6IjIwMjAtMTEtMTFUMTg6MTA6NTcuNzQ4VVRDIiwiaWRzOmNvbnRlbnRUeXBlIjpudWxsLCJpZHM6ZGVzY3JpcHRpb24iOlt7IkB2YWx1ZSI6IkV4YW1wbGUgU21hcnQtRGF0YS1BcHAgZm9yIGRlbW9uc3RyYXRpb24gcHVycG9zZXMiLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XSwiaWRzOnRoZW1lIjpudWxsLCJpZHM6c2hhcGVzR3JhcGgiOm51bGwsImlkczptb2RpZmllZCI6IjIwMjAtMTEtMTFUMTg6MTA6NTcuNzUwVVRDIiwiaWRzOmFzc2V0UmVmaW5lbWVudCI6bnVsbCwiaWRzOnJlcHJlc2VudGF0aW9uIjpbeyJAdHlwZSI6ImlkczpBcHBSZXByZXNlbnRhdGlvbiIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2FwcFJlcHJlc2VudGF0aW9uL2JlMmFkYjJmLTJhYWEtNDU5OS1iYWY5LTIzOWRmMTY2YWQwOCIsImlkczppbnN0YW5jZSI6bnVsbCwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6Y3JlYXRlZCI6bnVsbCwiaWRzOm1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS80MjMzZTdmYy00OGZlLTQ2NTMtOGY1Yi05ZmU2MzZkMGRjNmEiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi96aXAifSwiaWRzOnNoYXBlc0dyYXBoIjpudWxsLCJpZHM6bW9kaWZpZWQiOm51bGwsImlkczpkYXRhQXBwUnVudGltZUVudmlyb25tZW50IjoiRG9ja2VyIiwiaWRzOnJlcHJlc2VudGF0aW9uU3RhbmRhcmQiOm51bGwsImlkczpkYXRhQXBwRGlzdHJpYnV0aW9uU2VydmljZSI6Imh0dHBzOi8vYXBwc3RvcmUuZml0LmZyYXVuaG9mZXIuZGUvcmVnaXN0cnkiLCJpZHM6ZGF0YUFwcEluZm9ybWF0aW9uIjp7IkB0eXBlIjoiaWRzOlNtYXJ0RGF0YUFwcCIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL3NtYXJ0RGF0YUFwcC9jNTZiNGRhYi02MjcyLTRjOWQtOWJjYy1jNzQzZmM3MmM1MDYiLCJpZHM6YXBwRW5kcG9pbnQiOlt7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvYzkwMmVmODMtMGM4Zi00NDM3LWFjMmQtOGU2MTgxNTIzMzUwIiwiaWRzOnBhdGgiOiIvaW5wdXQiLCJpZHM6bGFuZ3VhZ2UiOm51bGwsImlkczphY2Nlc3NVUkwiOm51bGwsImlkczphcHBFbmRwb2ludFBvcnQiOjgwODAsImlkczphcHBFbmRwb2ludFR5cGUiOnsicHJvcGVydGllcyI6bnVsbCwiQGlkIjoiaWRzYzpJTlBVVF9FTkRQT0lOVCJ9LCJpZHM6aW5ib3VuZFBhdGgiOm51bGwsImlkczpvdXRib3VuZFBhdGgiOm51bGwsImlkczplbmRwb2ludERvY3VtZW50YXRpb24iOlsiaHR0cHM6Ly9hcHAuc3dhZ2dlcmh1Yi5jb20vYXBpcy9hcHAvMTMzNyJdLCJpZHM6YXBwRW5kcG9pbnRNZWRpYVR5cGUiOnsiQHR5cGUiOiJpZHM6SUFOQU1lZGlhVHlwZSIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2lBTkFNZWRpYVR5cGUvNGRlNzBjMDktMjgxNC00YzljLWI5OWMtZTI5MGJjNGY1ODZjIiwiaWRzOmZpbGVuYW1lRXh0ZW5zaW9uIjoiYXBwbGljYXRpb24vanNvbiJ9LCJpZHM6YXBwRW5kcG9pbnRQcm90b2NvbCI6IkhUVFAvMS4xIiwiaWRzOmVuZHBvaW50SW5mb3JtYXRpb24iOlt7IkB2YWx1ZSI6IkVuZHBvaW50IGZvciBhcHAgaW5wdXQgZGF0YSIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn1dfSx7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvZWMyODc2YmUtYTBiMi00ZWI5LWFiMTEtNjk5NDc2ODNmNjg5IiwiaWRzOnBhdGgiOiIvb3V0cHV0IiwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6YWNjZXNzVVJMIjpudWxsLCJpZHM6YXBwRW5kcG9pbnRQb3J0Ijo4MDgwLCJpZHM6YXBwRW5kcG9pbnRUeXBlIjp7InByb3BlcnRpZXMiOm51bGwsIkBpZCI6Imlkc2M6T1VUUFVUX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS9lYzI3YWYwZC0xNTRkLTQ0YWYtYWE1ZC03OWEyMTkwMGRhMTUiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBvdXRwdXQgZGF0YSIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn1dfSx7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvOTA2ZTdkODUtZDg4YS00MTNiLWJkYmEtMjExMjNmOWQ5OWJkIiwiaWRzOnBhdGgiOiIvY29uZmlnIiwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6YWNjZXNzVVJMIjpudWxsLCJpZHM6YXBwRW5kcG9pbnRQb3J0Ijo4MDgwLCJpZHM6YXBwRW5kcG9pbnRUeXBlIjp7InByb3BlcnRpZXMiOm51bGwsIkBpZCI6Imlkc2M6Q09ORklHX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS83OGRiOWMyNC0wZGNlLTRmMWUtODQxOC0xZjFhY2UwNzc1YmIiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBjb25maWd1cmF0aW9uIC8gcGFyYW1ldGVyaXphdGlvbiIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn1dfSx7IkB0eXBlIjoiaWRzOkFwcEVuZHBvaW50IiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vYXBwRW5kcG9pbnQvZGJmYmZjNWYtYWExNy00ODUzLTlkZGEtNzBlOTVmNWQ5ODA5IiwiaWRzOnBhdGgiOiIvc3RhdHVzIiwiaWRzOmxhbmd1YWdlIjpudWxsLCJpZHM6YWNjZXNzVVJMIjpudWxsLCJpZHM6YXBwRW5kcG9pbnRQb3J0Ijo4MDgwLCJpZHM6YXBwRW5kcG9pbnRUeXBlIjp7InByb3BlcnRpZXMiOm51bGwsIkBpZCI6Imlkc2M6U1RBVFVTX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS82MTYwMzQ5MC1lZmM4LTQyMTAtYmMwZC04N2M4OTM3YTUzNGMiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBzdGF0dXMgaW5mb3JtYXRpb24iLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XX0seyJAdHlwZSI6ImlkczpBcHBFbmRwb2ludCIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2FwcEVuZHBvaW50LzBkYzk1M2RhLTBkZmQtNGM5NS1hMmMxLTIzNTg5OWFlOGUxNCIsImlkczpwYXRoIjoiL3Byb2Nlc3MiLCJpZHM6bGFuZ3VhZ2UiOm51bGwsImlkczphY2Nlc3NVUkwiOm51bGwsImlkczphcHBFbmRwb2ludFBvcnQiOjgwODAsImlkczphcHBFbmRwb2ludFR5cGUiOnsicHJvcGVydGllcyI6bnVsbCwiQGlkIjoiaWRzYzpQUk9DRVNTX0VORFBPSU5UIn0sImlkczppbmJvdW5kUGF0aCI6bnVsbCwiaWRzOm91dGJvdW5kUGF0aCI6bnVsbCwiaWRzOmVuZHBvaW50RG9jdW1lbnRhdGlvbiI6WyJodHRwczovL2FwcC5zd2FnZ2VyaHViLmNvbS9hcGlzL2FwcC8xMzM3Il0sImlkczphcHBFbmRwb2ludE1lZGlhVHlwZSI6eyJAdHlwZSI6ImlkczpJQU5BTWVkaWFUeXBlIiwiQGlkIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2F1dG9nZW4vaUFOQU1lZGlhVHlwZS80MzU5ZTdmZi1lYWVlLTQyNTctYTc5MC1mOGM4N2QwNzVhYTgiLCJpZHM6ZmlsZW5hbWVFeHRlbnNpb24iOiJhcHBsaWNhdGlvbi9qc29uIn0sImlkczphcHBFbmRwb2ludFByb3RvY29sIjoiSFRUUC8xLjEiLCJpZHM6ZW5kcG9pbnRJbmZvcm1hdGlvbiI6W3siQHZhbHVlIjoiRW5kcG9pbnQgZm9yIGFwcCBwcm9jZXNzaW5nIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifV19LHsiQHR5cGUiOiJpZHM6QXBwRW5kcG9pbnQiLCJAaWQiOiJodHRwczovL3czaWQub3JnL2lkc2EvYXV0b2dlbi9hcHBFbmRwb2ludC9jOGE5ZTIyZC03MWQ3LTQzZWItYWQ0YS1hOGM2YmZhYjAxZTUiLCJpZHM6cGF0aCI6Ii91c2FnZSIsImlkczpsYW5ndWFnZSI6bnVsbCwiaWRzOmFjY2Vzc1VSTCI6bnVsbCwiaWRzOmFwcEVuZHBvaW50UG9ydCI6ODA4MCwiaWRzOmFwcEVuZHBvaW50VHlwZSI6eyJwcm9wZXJ0aWVzIjpudWxsLCJAaWQiOiJpZHNjOlVTQUdFX1BPTElDWV9FTkRQT0lOVCJ9LCJpZHM6aW5ib3VuZFBhdGgiOm51bGwsImlkczpvdXRib3VuZFBhdGgiOm51bGwsImlkczplbmRwb2ludERvY3VtZW50YXRpb24iOlsiaHR0cHM6Ly9hcHAuc3dhZ2dlcmh1Yi5jb20vYXBpcy9hcHAvMTMzNyJdLCJpZHM6YXBwRW5kcG9pbnRNZWRpYVR5cGUiOnsiQHR5cGUiOiJpZHM6SUFOQU1lZGlhVHlwZSIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2lBTkFNZWRpYVR5cGUvYzMxYzBiMDgtZTk5ZC00ZmM3LWE2N2ItMzJlOTkwMTVjOGRiIiwiaWRzOmZpbGVuYW1lRXh0ZW5zaW9uIjoiYXBwbGljYXRpb24vanNvbiJ9LCJpZHM6YXBwRW5kcG9pbnRQcm90b2NvbCI6IkhUVFAvMS4xIiwiaWRzOmVuZHBvaW50SW5mb3JtYXRpb24iOlt7IkB2YWx1ZSI6IkVuZHBvaW50IGZvciBhcHAgdXNhZ2UgcG9saWNpZXMiLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XX1dLCJpZHM6YXBwRW52aXJvbm1lbnRWYXJpYWJsZXMiOiIkZGJVc2VyPXNhOyRkcFBhc3N3ZD1wYXNzd2QiLCJpZHM6YXBwRG9jdW1lbnRhdGlvbiI6IlBsYWNlIGZvciBhbiBhcHAtcmVsYXRlZCBodW1hbi1yZWFkYWJsZSBkb2N1bWVudGF0aW9uIiwiaWRzOmFwcFN0b3JhZ2VDb25maWd1cmF0aW9uIjoiLXYgL2RhdGEifX1dLCJpZHM6Y29udGVudFN0YW5kYXJkIjpudWxsLCJpZHM6a2V5d29yZCI6W3siQHZhbHVlIjoiRGVtbyIsIkBsYW5ndWFnZSI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9jb2RlL0VOIn0seyJAdmFsdWUiOiJFeGFtcGxlIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifSx7IkB2YWx1ZSI6IlNtYXJ0LURhdGEtQXBwIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifV0sImlkczpzYW1wbGUiOm51bGwsImlkczpzb3ZlcmVpZ24iOm51bGwsImlkczpyZXNvdXJjZVBhcnQiOm51bGwsImlkczpzcGF0aWFsQ292ZXJhZ2UiOm51bGwsImlkczpjb250ZW50UGFydCI6bnVsbCwiaWRzOmNvbnRyYWN0T2ZmZXIiOm51bGwsImlkczpwdWJsaXNoZXIiOm51bGwsImlkczpzdGFuZGFyZExpY2Vuc2UiOiJodHRwczovL3d3dy5hcGFjaGUub3JnL2xpY2Vuc2VzL0xJQ0VOU0UtMi4wIiwiaWRzOmN1c3RvbUxpY2Vuc2UiOiJodHRwczovL3d3dy5hcGFjaGUub3JnL2xpY2Vuc2VzL0xJQ0VOU0UtMi4wIiwiaWRzOmRlZmF1bHRSZXByZXNlbnRhdGlvbiI6bnVsbCwiaWRzOnRlbXBvcmFsUmVzb2x1dGlvbiI6bnVsbCwiaWRzOnRlbXBvcmFsQ292ZXJhZ2UiOm51bGwsImlkczpyZXNvdXJjZUVuZHBvaW50IjpbeyJAdHlwZSI6ImlkczpDb25uZWN0b3JFbmRwb2ludCIsIkBpZCI6Imh0dHBzOi8vdzNpZC5vcmcvaWRzYS9hdXRvZ2VuL2Nvbm5lY3RvckVuZHBvaW50L2ZiNTk5NDY2LTVmNTQtNGRmMS1iNzUzLWZkZDM0ODQ5ODhjNSIsImlkczpwYXRoIjoiLyIsImlkczphY2Nlc3NVUkwiOiJodHRwczovL2FwcHN0b3JlLmZpdC5mcmF1bmhvZmVyLmRlIiwiaWRzOmluYm91bmRQYXRoIjpudWxsLCJpZHM6b3V0Ym91bmRQYXRoIjpudWxsLCJpZHM6ZW5kcG9pbnREb2N1bWVudGF0aW9uIjpbImh0dHA6Ly9hcHBzdG9yZS5maXQuZnJhdW5ob2Zlci5kZS9hcHAvYXBwSWQiXSwiaWRzOmVuZHBvaW50SW5mb3JtYXRpb24iOlt7IkB2YWx1ZSI6IlRoaXMgaXMgdGhlIGRlZmF1bHQgZW5kcG9pbnQgb2YgdGhlIEZyYXVuaG9mZXIgRklUIEFwcFN0b3JlIiwiQGxhbmd1YWdlIjoiaHR0cHM6Ly93M2lkLm9yZy9pZHNhL2NvZGUvRU4ifV0sImlkczplbmRwb2ludEFydGlmYWN0IjpudWxsfV0sImlkczphY2NydWFsUGVyaW9kaWNpdHkiOm51bGwsImlkczp0aXRsZSI6W3siQHZhbHVlIjoiSURTU21hcnREYXRhQXBwVGVtcGxhdGUiLCJAbGFuZ3VhZ2UiOiJodHRwczovL3czaWQub3JnL2lkc2EvY29kZS9FTiJ9XX0="
