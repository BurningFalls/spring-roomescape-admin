## 주요 기능

- [X] `/admin` 요청 시, 어드민 메인 페이지 응답
- [X] `/admin/reservation` 요청 시, 예약 관리 페이지 응답
- [X] `/admin/time` 요청 시, 시간 관리 페이지 응답
- [X] 시간 추가 API
- [X] 시간 조회 API
- [X] 시간 삭제 API
- [X] 예약 추가 API
- [X] 예약 조회 API
- [X] 예약 삭제 API

## 시간 추가 API

### request

```
POST /times HTTP/1.1
content-type: application/json

{
    "startAt": "10:00"
}
```

### response

``` 
HTTP/1.1 201
Content-Type: application/json

{
    "id": 1,
    "startAt": "10:00"
}
```

## 시간 조회 API

### request

```
GET /times HTTP/1.1
```

### response

```
HTTP/1.1 200
Content-Type: application/json

[
   {
        "id": 1,
        "startAt": "10:00"
    }
]
```

## 시간 삭제 API

### request

```
DELETE /times/1 HTTP/1.1
```

### response

```
HTTP/1.1 204
```

## 예약 추가 API

### request

```
POST /reservations HTTP/1.1
content-type: application/json

{
    "date": "2023-08-05",
    "name": "브라운",
    "timeId": 1
}
```

### response

```
HTTP/1.1 201
Content-Type: application/json

{
    "id": 1,
    "name": "브라운",
    "date": "2023-08-05",
    "time" : {
        "id": 1,
        "startAt" : "10:00"
    }
}
```

## 예약 조회 API

### request

```
GET /reservations HTTP/1.1
```

### response

```
HTTP/1.1 200
Content-Type: application/json

[
    {
        "id": 1,
        "name": "브라운",
        "date": "2023-08-05",
        "time": {
            "id": 1,
            "startAt": "10:00"
        }
    }
]
```

## 예약 삭제 API

### request

```
DELETE /reservations/1 HTTP/1.1
```

### response

```
HTTP/1.1 204
```