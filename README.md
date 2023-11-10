# 微信小程序与后台管理系统

## 简介

本项目是一个微信小程序及其后台管理系统，主要用于提供一个高效、稳定的小程序用户界面和一个功能强大的管理平台。项目采用了Spring Boot框架，结合了Flyway进行数据库迁移管理，使用Docker容器化部署，数据库采用PostgreSQL，同时还整合了Guava工具库和Spring Security进行安全控制。

## 功能特点

- **订单管理**: 支持订单的添加、删除，以及管理备注的添加(adminDesc)。
- **屋型信息**: 提供屋型列表的获取，支持过滤忽略3D数据(ignore3DData)和自定义选项(canCustom)。
- **用户管理**: 支持获取用户订单数量（排除已删除订单），以及按预约订单数量查询用户的分页功能。
- **数据安全**: 通过Spring Security实现了安全的用户登录及数据访问。
- **后台管理**: 后台管理界面支持对订单、用户、屋型等信息的管理。
- **Docker支持**: 项目可通过Docker容器化部署，提高了部署效率和环境一致性。
- **数据库迁移**: 采用Flyway进行数据库版本控制，确保数据库结构的版本与应用程序代码同步。
- **自动化构建和部署**: 利用Maven和GitHub Actions实现自动化构建和Docker镜像的上传。

## 技术栈

- **Spring Boot**: 作为项目的基础框架，提供了快速开发的能力和众多的起步依赖。
- **Flyway**: 管理和应用数据库迁移。
- **Docker**: 容器化部署应用和其依赖环境。
- **PostgreSQL**: 作为后台数据库，提供强大的数据存储能力。
- **Guava**: Google的核心Java库，用于提高代码质量和可读性。
- **Spring Security**: 用于提供安全的用户认证和权限控制。

## 项目结构


```
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── changmin
    │   │           └── cm_backend
    │   │               ├── CmBackendApplication.java
    │   │               ├── component
    │   │               ├── config
    │   │               ├── controller
    │   │               ├── exceptions
    │   │               ├── mapper
    │   │               ├── model
    │   │               ├── service
    │   │               └── util
    │   └── resources
    │       ├── application-linux.yml
    │       ├── application-prod.yml
    │       ├── application.yml
    │       ├── db
    │       │   └── migration
    │       ├── mapper
    │       ├── static
    │       └── templates
```



## 安装与使用

### 环境要求

- Java 8或更高版本
- Maven
- Docker
- PostgreSQL

### 构建项目

1. 克隆仓库到本地
```sh
git clone git@github.com:zzturn/ss_changmin.git
```

2. 使用Maven构建项目
```sh
mvn clean install
```

3. 构建Docker镜像并上传
```sh
mvn package docker:build
```

4. 运行Docker容器
```sh
docker run -d -p 9901:9901 ss_changmin
```

## 致谢

在此，我们特别感谢JetBrains提供的开发工具支持，JetBrains的各类IDE（如IntelliJ IDEA）极大地提升了我们的开发效率。我们的项目能够快速迭代和稳定运行，JetBrains的贡献不可或缺。

## 许可证

本项目采用[GNU](https://github.com/zzturn/ss_changmin/blob/master/LICENSE)许可证，详情请参阅LICENSE文件。

## 联系方式

如有任何问题或者建议，请与我们联系。

---

感谢您对我们的项目感兴趣，我们期待您的使用反馈和贡献！

