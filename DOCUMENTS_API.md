# Documents Feature API Documentation

## Overview

This API provides endpoints for managing documents, document categories, tags, and document versions. It supports both admin and user operations.

---

## Table of Contents

- [Admin Endpoints](#admin-endpoints)
  - [Documents](#documents-admin)
  - [Document Categories](#document-categories-admin)
  - [Document Versions](#document-versions-admin)
  - [Tags](#tags-admin)
- [User Endpoints](#user-endpoints)
  - [Documents](#documents-user)
  - [Document Categories](#document-categories-user)
- [Data Models](#data-models)
  - [POST/PUT Request Bodies](#postput-request-bodies)
  - [Response DTOs](#response-dtos)

---

## Admin Endpoints

### Documents (Admin)

Base URL: `/document/admin`

| Method | Endpoint                   | Description                  |
| ------ | -------------------------- | ---------------------------- |
| POST   | `/create`                  | Create a new document        |
| PUT    | `/update/{id}`             | Update an existing document  |
| DELETE | `/delete/{id}`             | Delete a document by ID      |
| GET    | `/getById/{id}`            | Get document by ID           |
| GET    | `/byCategory/{categoryId}` | Get documents by category ID |
| GET    | `/getAll`                  | Get all documents            |

#### Create Document (POST `/create`)

- **Content-Type:** `multipart/form-data`
- **Body:**
  - `title` (string, required)
  - `category` (object, required) — see [DocumentCategory](#documentcategory)
  - `tags` (array of objects, optional) — see [Tag](#tag)
  - `userAllowed` (array of enums, required) — see [AllowedUsers](#allowedusers)
  - `file` (file, required)

#### Update Document (PUT `/update/{id}`)

- **Content-Type:** `multipart/form-data`
- **Body:**
  - `title` (string, optional)
  - `category` (object, optional)
  - `tags` (array of objects, optional)
  - `allowedUsers` (array of enums, optional)
  - `file` (file, optional)

---

### Document Categories (Admin)

Base URL: `/document/admin/category`

| Method | Endpoint              | Description               |
| ------ | --------------------- | ------------------------- |
| POST   | `/create`             | Create a new category     |
| PUT    | `/update/{id}`        | Update a category         |
| DELETE | `/delete/{id}`        | Delete a category         |
| GET    | `/getById/{id}`       | Get category by ID        |
| GET    | `/getAll`             | Get all categories        |
| GET    | `/search?keyword=...` | Search categories by name |

#### Create Category (POST `/create`)

- **Content-Type:** `application/json`
- **Body:**
  - `name` (string, required)

#### Update Category (PUT `/update/{id}`)

- **Content-Type:** `application/json`
- **Body:**
  - `name` (string, required)

---

### Document Versions (Admin)

Base URL: `/document/admin/version/`

| Method | Endpoint                      | Description                     |
| ------ | ----------------------------- | ------------------------------- |
| GET    | `/getAllByDocId/{documentId}` | Get all versions by document ID |
| GET    | `/getByVersionId/{versionId}` | Get version by version ID       |
| DELETE | `/delete/{versionId}`         | Delete a document version       |

---

### Tags (Admin)

Base URL: `/document/admin/tag`

| Method | Endpoint              | Description         |
| ------ | --------------------- | ------------------- |
| POST   | `/create`             | Create a new tag    |
| PUT    | `/update/{id}`        | Update a tag        |
| DELETE | `/delete/{id}`        | Delete a tag        |
| GET    | `/getAllTags`         | Get all tags        |
| GET    | `/getById/{id}`       | Get tag by ID       |
| GET    | `/search?keyword=...` | Search tags by name |

#### Create Tag (POST `/create`)

- **Content-Type:** `application/json`
- **Body:**
  - `name` (string, required)

#### Update Tag (PUT `/update/{id}`)

- **Content-Type:** `application/json`
- **Body:**
  - `name` (string, required)

---

## User Endpoints

### Documents (User)

Base URL: `/document/user`

| Method | Endpoint                   | Description                        |
| ------ | -------------------------- | ---------------------------------- |
| GET    | `/getById/{id}`            | Get document summary by ID         |
| GET    | `/byCategory/{categoryId}` | Get document summaries by category |
| GET    | `/getAll`                  | Get all document summaries         |

### Document Categories (User)

Base URL: `/document/user/category`

| Method | Endpoint              | Description                |
| ------ | --------------------- | -------------------------- |
| GET    | `/getById/{id}`       | Get category summary by ID |
| GET    | `/getAll`             | Get all category summaries |
| GET    | `/search?keyword=...` | Search categories by name  |

---

## Data Models

### POST/PUT Request Bodies

#### DocumentCreateDto (multipart/form-data)

| Field       | Type               | Required | Description                      |
| ----------- | ------------------ | -------- | -------------------------------- |
| title       | string             | Yes      | Document title                   |
| category    | object (see below) | Yes      | Document category object         |
| tags        | array of objects   | No       | List of tag objects              |
| userAllowed | array of enums     | Yes      | Allowed users (see AllowedUsers) |
| file        | file               | Yes      | Document file                    |

##### DocumentCategory (object)

| Field          | Type   | Description     |
| -------------- | ------ | --------------- |
| id             | Long   | Category ID     |
| name           | String | Category name   |
| normalizedName | String | Normalized name |

##### Tag (object)

| Field | Type   | Description |
| ----- | ------ | ----------- |
| id    | Long   | Tag ID      |
| name  | String | Tag name    |

##### AllowedUsers (enum)

- ALL
- SUPER_ADMIN
- ADMIN
- STUDENT
- BATCH2023
- BATCH2024
- BATCH2025
- BATCH2026
- BATCH2027
- BATCH2028

#### DocumentUpdateDto (multipart/form-data)

| Field        | Type               | Required | Description                      |
| ------------ | ------------------ | -------- | -------------------------------- |
| title        | string             | No       | Document title                   |
| category     | object (see above) | No       | Document category object         |
| tags         | array of objects   | No       | List of tag objects              |
| allowedUsers | array of enums     | No       | Allowed users (see AllowedUsers) |
| file         | file               | No       | Document file                    |

#### DocumentCategoryCreateDto (application/json)

| Field | Type   | Required | Description   |
| ----- | ------ | -------- | ------------- |
| name  | String | Yes      | Category name |

#### DocumentCategoryUpdateDto (application/json)

| Field | Type   | Required | Description   |
| ----- | ------ | -------- | ------------- |
| name  | String | Yes      | Category name |

#### TagCreateDto (application/json)

| Field | Type   | Required | Description |
| ----- | ------ | -------- | ----------- |
| name  | String | Yes      | Tag name    |

#### TagUpdateDto (application/json)

| Field | Type   | Required | Description |
| ----- | ------ | -------- | ----------- |
| name  | String | Yes      | Tag name    |

#### DocumentVersionCreateDto (multipart/form-data)

| Field      | Type | Required | Description   |
| ---------- | ---- | -------- | ------------- |
| documentId | Long | Yes      | Document ID   |
| file       | file | Yes      | Document file |

#### DocumentVersionUpdateDto (multipart/form-data)

| Field           | Type    | Required | Description            |
| --------------- | ------- | -------- | ---------------------- |
| file            | file    | No       | Document file          |
| isLatestVersion | Boolean | No       | Mark as latest version |

---

### Response DTOs

#### DocumentResponseDto

- id: Long
- title: String
- category: DocumentCategory
- allowedUsers: Set<AllowedUsers>
- tags: Set<Tag>
- latestFilePath: String
- versionNumber: Long
- uploadedAt: LocalDateTime
- uploadedBy: String
- updatedAt: LocalDateTime

#### DocumentSummaryDto

- title: String
- category: DocumentCategory
- tags: Set<String>
- latestFilePath: String
- uploadedAt: LocalDateTime
- updatedAt: LocalDateTime

#### DocumentCategoryResponseDto

- id: Long
- name: String
- normalizedName: String

#### DocumentCategorySummaryDto

- id: Long
- name: String

#### TagResponseDto

- id: Long
- name: String

#### DocumentVersionResponseDto

- id: Long
- documentName: String
- versionNumber: Long
- fileUrl: String
- uploadedAt: LocalDateTime
- uploadedByEmail: String
- isLatestVersion: Boolean

#### DocumentVersionSummaryDto

- documentName: String
- versionNumber: Long
- fileUrl: String
- uploadedAt: LocalDateTime

---

## Notes

- All admin endpoints require admin privileges.
- For file uploads, use `multipart/form-data` encoding.
- For enums and object references, provide the appropriate IDs or names as required by your client implementation.
