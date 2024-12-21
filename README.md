---
name: OSS 프로젝트
about: 3~4주차 과제인 OSS 프로젝트 설명
title: "이름 - 라이브러리 주제"
labels: ''
assignees: ''
---

## 프로젝트 이름

자바 컬렉션 유틸리티 라이브러리

## 개요

자바에서 자주 사용하는 컬렉션을 좀 더 편하게 다룰 수 있도록 돕는 라이브러리입니다. 이 라이브러리는 가독성과 생산성을 향상시키기 위해 유틸리티 메서드를 제공합니다.

## 설치 방법

1. Maven 사용 시
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.develpsh</groupId>
    <artifactId>collection-utils-lib</artifactId>
    <version>v1.0.0</version>
</dependency>
```

2. Gradle 사용 시 build.gradle에 아래 내용을 추가

```xml
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.develpsh:collection-utils-lib:v1.0.0'
}
```
