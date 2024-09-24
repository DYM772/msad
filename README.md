# MSAD
* 광고 플랫폼에서 발생하는 시나리오를 바탕으로 이벤트 스토밍
* 광고 플랫폼의 서비스를 분리하여 MSA 설계
* 클라우드 환경에서 애플리케이션 개발

## 아키텍처 설계
### 아키텍처 구성도

## 서비스 분리/설계
### 이벤트 스토밍
![eventstorming](https://github.com/user-attachments/assets/ddfb057f-bf8c-4352-8e96-57fca7f8bd73)
1. 운영자가 광고 인벤토리를 설정하고 광고 대행사가 광고 소재와 광고를 등록한다.
2. 매체사가 등록된 광고를 승인하고 사용자에게 광고 노출이 실행된다.
3. 매체사가 광고 승인 시 광고 인벤토리가 초과되었다면 광고 노출을 중지하고 광고를 종료 상태로 변경한다.
4. 운영자가 전체 광고 정보를 확인한다.

## MSA 구현
### SAGA 패턴
1. 인벤토리, 광고 소재, 광고 등록

<details>
<summary><strong>http POST <EXTERNAL_IP>/inventories id=1 target=student inventory=200000</strong></summary>
  
```
{
    "_links": {
        "inventory": {
            "href": "http://20.214.194.178/inventories/1"
        },
        "self": {
            "href": "http://20.214.194.178/inventories/1"
        }
    },
    "inventory": 200000,
    "target": "student"
}
```
</details>

<details>
<summary><strong>http POST <EXTERNAL_IP>/materials id=1 name=video_1 type=mp4 size=4096 url=cdn.com/video_1</strong></summary>
  
```
{
    "_links": {
        "material": {
            "href": "http://20.214.194.178/materials/1"
        },
        "self": {
            "href": "http://20.214.194.178/materials/1"
        }
    },
    "name": "video_1",
    "size": 4096,
    "type": "mp4",
    "url": "cdn.com/video_1"
}
```
</details>

<details>
<summary><strong>http POST <EXTERNAL_IP>/advertisements id=1 materialId=1 materialName=video_1 materialUrl=cdn.com/video_1 title=kobako content=ad budget=1000000 target=student targetImpressions=100000 status=READY startDate=2024-09-01 endDate=2024-09-15</strong></summary>
  
```
{
    "_links": {
        "advertisement": {
            "href": "http://20.214.194.178/advertisements/1"
        },
        "self": {
            "href": "http://20.214.194.178/advertisements/1"
        }
    },
    "budget": 1000000,
    "content": "ad",
    "endDate": "2024-09-15T00:00:00.000+00:00",
    "materialId": 1,
    "materialName": "video_1",
    "materialUrl": "cdn.com/video_1",
    "startDate": "2024-09-01T00:00:00.000+00:00",
    "status": "READY",
    "target": "student",
    "targetImpressions": 100000,
    "title": "kobako"
}
```
</details>

2. 광고 승인 후 광고 노출, 인벤토리, 광고 상태 확인

<strong>http PATCH <EXTERNAL_IP>/advertisements/approval/1 status=RUNNING</strong>

<details>
<summary><strong>http GET <EXTERNAL_IP>/deliveries/1</strong></summary>
  
```
{
    "_links": {
        "delivery": {
            "href": "http://20.214.194.178/deliveries/1"
        },
        "self": {
            "href": "http://20.214.194.178/deliveries/1"
        }
    },
    "adId": 1,
    "endDate": "2024-09-15T00:00:00.000+00:00",
    "materialId": 1,
    "materialName": "video_1",
    "materialUrl": "cdn.com/video_1",
    "startDate": "2024-09-01T00:00:00.000+00:00",
    "status": "ACTIVE",
    "target": "student",
    "targetImpressions": 100000
}
```
</details>

<details>
<summary><strong>http GET <EXTERNAL_IP>/inventories/1</strong></summary>
  
```
{
    "_links": {
        "inventory": {
            "href": "http://20.214.194.178/inventories/1"
        },
        "self": {
            "href": "http://20.214.194.178/inventories/1"
        }
    },
    "inventory": 100000,
    "target": "student"
}
```
</details>

<details>
<summary><strong>http GET <EXTERNAL_IP>/advertisements/1</strong></summary>
  
```
{
    "_links": {
        "advertisement": {
            "href": "http://20.214.194.178/advertisements/1"
        },
        "self": {
            "href": "http://20.214.194.178/advertisements/1"
        }
    },
    "budget": 1000000,
    "content": "ad",
    "endDate": "2024-09-15T00:00:00.000+00:00",
    "materialId": 1,
    "materialName": "video_1",
    "materialUrl": "cdn.com/video_1",
    "startDate": "2024-09-01T00:00:00.000+00:00",
    "status": "RUNNING",
    "target": "student",
    "targetImpressions": 100000,
    "title": "kobako"
}
```
</details>

### 보상 트랜잭션
1. 광고 등록, 광고 승인

<details>
<summary><strong>http POST <EXTERNAL_IP>/advertisements id=2 materialId=1 materialName=video_1 materialUrl=cdn.com/video_1 title=kobako content=ad budget=1500000 target=student targetImpressions=150000 status=READY startDate=2024-10-01 endDate=2024-10-15</strong></summary>

```
{
    "_links": {
        "advertisement": {
            "href": "http://20.214.194.178/advertisements/2"
        },
        "self": {
            "href": "http://20.214.194.178/advertisements/2"
        }
    },
    "budget": 1500000,
    "content": "ad",
    "endDate": "2024-10-15T00:00:00.000+00:00",
    "materialId": 1,
    "materialName": "video_1",
    "materialUrl": "cdn.com/video_1",
    "startDate": "2024-10-01T00:00:00.000+00:00",
    "status": "READY",
    "target": "student",
    "targetImpressions": 150000,
    "title": "kobako"
}
```
</details>

<strong>http PATCH <EXTERNAL_IP>/advertisements/approval/2 status=RUNNING</strong>

2. 광고 인벤토리 초과로 광고 노출 및 광고 상태 변경

<details>
<summary><strong>http GET <EXTERNAL_IP>/inventories/1</strong></summary>
  
```
{
    "_links": {
        "inventory": {
            "href": "http://20.214.194.178/inventories/1"
        },
        "self": {
            "href": "http://20.214.194.178/inventories/1"
        }
    },
    "inventory": 100000,
    "target": "student"
}
```
</details>

<details>
<summary><strong>http GET <EXTERNAL_IP>/deliveries/1</strong></summary>
  
```
{
    "_links": {
        "delivery": {
            "href": "http://20.214.194.178/deliveries/2"
        },
        "self": {
            "href": "http://20.214.194.178/deliveries/2"
        }
    },
    "adId": 2,
    "endDate": "2024-10-15T00:00:00.000+00:00",
    "materialId": 1,
    "materialName": "video_1",
    "materialUrl": "cdn.com/video_1",
    "startDate": "2024-10-01T00:00:00.000+00:00",
    "status": "STOP",
    "target": "student",
    "targetImpressions": 150000
}
```
</details>

<details>
<summary><strong>http GET <EXTERNAL_IP>/advertisements/2</strong></summary>
  
```
{
    "_links": {
        "advertisement": {
            "href": "http://20.214.194.178/advertisements/2"
        },
        "self": {
            "href": "http://20.214.194.178/advertisements/2"
        }
    },
    "budget": 1500000,
    "content": "ad",
    "endDate": "2024-10-15T00:00:00.000+00:00",
    "materialId": 1,
    "materialName": "video_1",
    "materialUrl": "cdn.com/video_1",
    "startDate": "2024-10-01T00:00:00.000+00:00",
    "status": "CLOSED",
    "target": "student",
    "targetImpressions": 150000,
    "title": "kobako"
}
```
</details>

### 게이트웨이
Ingress를 사용하여 트래픽을 라우팅
```
apiVersion: networking.k8s.io/v1
kind: "Ingress"
metadata: 
  name: "msad-ingress"
  namespace: default
  annotations: 
    nginx.ingress.kubernetes.io/ssl-redirect: "false"
    ingressclass.kubernetes.io/is-default-class: "true"
spec: 
  ingressClassName: nginx
  rules: 
    - host: ""
      http: 
        paths:
          - path: /materials
            pathType: Prefix
            backend: 
              service:
                name: material
                port:
                  number: 8080
          - path: /advertisements
            pathType: Prefix
            backend: 
              service:
                name: advertisement
                port:
                  number: 8080
          - path: /deliveries
            pathType: Prefix
            backend: 
              service:
                name: delivery
                port:
                  number: 8080
          - path: /inventories
            pathType: Prefix
            backend: 
              service:
                name: inventory
                port:
                  number: 8080
          - path: /adStatuses
            pathType: Prefix
            backend: 
              service:
                name: ad-status
                port:
                  number: 8080
```

![ingress](https://github.com/user-attachments/assets/ecb58481-2e1c-4344-802e-231ad2210bb3)

### CQRS 패턴
1. 전체 광고 정보 확인

<details>
<summary><strong>http GET <EXTERNAL_IP>/adStatuses</strong></summary>
  
```
{
    "_embedded": {
        "adStatuses": [
            {
                "_links": {
                    "adStatus": {
                        "href": "http://20.214.194.178/adStatuses/1"
                    },
                    "self": {
                        "href": "http://20.214.194.178/adStatuses/1"
                    }
                },
                "adId": 1,
                "adStatus": "READY",
                "budget": 1000000,
                "content": "ad",
                "deliveryStatus": "ACTIVE",
                "endDate": "2024-09-15T00:00:00.000+00:00",
                "materialId": 1,
                "materialName": "video_1",
                "materialUrl": "cdn.com/video_1",
                "startDate": "2024-09-01T00:00:00.000+00:00",
                "target": "student",
                "targetImpressions": 100000,
                "title": "kobako"
            },
            {
                "_links": {
                    "adStatus": {
                        "href": "http://20.214.194.178/adStatuses/2"
                    },
                    "self": {
                        "href": "http://20.214.194.178/adStatuses/2"
                    }
                },
                "adId": 2,
                "adStatus": "CLOSED",
                "budget": 1500000,
                "content": "ad",
                "deliveryStatus": "STOP",
                "endDate": "2024-10-15T00:00:00.000+00:00",
                "materialId": 1,
                "materialName": "video_1",
                "materialUrl": "cdn.com/video_1",
                "startDate": "2024-10-01T00:00:00.000+00:00",
                "target": "student",
                "targetImpressions": 150000,
                "title": "kobako"
            }
        ]
    },
    "_links": {
        "profile": {
            "href": "http://20.214.194.178/profile/adStatuses"
        },
        "search": {
            "href": "http://20.214.194.178/adStatuses/search"
        },
        "self": {
            "href": "http://20.214.194.178/adStatuses"
        }
    },
    "page": {
        "number": 0,
        "size": 20,
        "totalElements": 2,
        "totalPages": 1
    }
}
```
</details>

## 클라우드 배포(K8s)
### CI/CD Pipeline
- CI

![CI1](https://github.com/user-attachments/assets/bb547698-f58f-4acc-bd25-3e7af69e56dd)
![CI2](https://github.com/user-attachments/assets/ad52e6cc-b65f-4ece-acd9-bf028a3e332d)

- CD

![CD1](https://github.com/user-attachments/assets/c729ab33-5331-458c-9cd4-a1383acdca22)
![CD2](https://github.com/user-attachments/assets/32561e72-5366-478e-8674-46c0d9005363)


## 클라우드 DevOps
### Auto Scaling
1. Auto Scaling 설정
```
kubectl autoscale deployment material --cpu-percent=50 --min=1 --max=3
```
2. 부하 테스트 실행

![HPA](https://github.com/user-attachments/assets/504f98f0-e5f8-41d4-b660-29ac1f3cecd7)

3. Auto Scaling 실행

![HPA2](https://github.com/user-attachments/assets/09e2f897-2e5a-4af2-8e64-a77554707f66)
![HPA3](https://github.com/user-attachments/assets/b5d0f3fd-5c15-40d7-9ef3-88514b4a257a)

### ConfigMap
1. ConfigMap 생성
```
kubectl create configmap my-config --from-literal=class=MSA --from-literal=Lab=ConfigMap
```
2. Pod에 ConfigMap 설정
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: material
  labels:
    app: material
spec:
  replicas: 1
  selector:
    matchLabels:
      app: material
  template:
    metadata:
      labels:
        app: material
        sidecar.istio.io/inject: "true"
    spec:
      containers:
        - name: material
          image: "user02.azurecr.io/material:latest"
          ports:
            - containerPort: 8080
          env:  //ConfigMap 설정
            - name: CLASS
              valueFrom:
                configMapKeyRef:
                  name: my-config
                  key: class
```
3. ConfigMap 변수 확인

![CM](https://github.com/user-attachments/assets/b02a55e1-5753-44d7-b444-f87c96b26b6e)

### Readiness Probe
1. yaml 파일에서 Readiness Probe 설정
```
          livenessProbe:  //Readiness Probe 설정
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 120
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 5
```
2. 무정지 배포 확인

![RP](https://github.com/user-attachments/assets/37650ac5-9642-4a9e-91a9-3390479155de)

### Service Mesh
- yaml 파일의 label에서 sidecar로 istio를 injection
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: material
  labels:
    app: material
spec:
  replicas: 1
  selector:
    matchLabels:
      app: material
  template:
    metadata:
      labels:
        app: material
        sidecar.istio.io/inject: "true" //sidecar로 istio envoy를 injection
    spec:
      containers:
        - name: material
          image: "user02.azurecr.io/material:latest"
          ports:
            - containerPort: 8080
```
- pod에 sidecar 컨테이너 추가됨

![SM2](https://github.com/user-attachments/assets/724139e5-8eb8-4573-90d1-d2606a63ea7a)

### Loggregation/Monitoring
- kiali

![kiali](https://github.com/user-attachments/assets/7397dd1a-b9e0-4bd9-bf78-566b98bc2789)

- jaeger

![jaeger](https://github.com/user-attachments/assets/9a343e9c-aaa0-4171-b39c-bf4938a2057f)
