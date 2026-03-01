# Analysis — Maximum Subarray API (kata-k4537d54)

---

## 1. User Story (MVP but semi technical story)

**As a** user, 
**I want** to submit an array of integers via a REST API
**So that** I receive the maximum sum of any contiguous subarray.

**Acceptance Criteria:**
- Given an empty array, the API returns `0`
- Given an all-negative array, the API returns `0`
- Given a mixed array (e.g. `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`), the API returns `6`
- Given an all-positive array (e.g. `[1, 2, 3]`), the API returns `6`
- Malformed or null input returns HTTP `400 Bad Request`

---

## 2. Data Dictionary

### 2.1 Request — `SubarrayRequest`

| Field     | Type    | Required | Constraints          | Description                                    |
|-----------|---------|----------|----------------------|------------------------------------------------|
| `numbers` | `int[]` | Yes      | Not null; may be empty | Ordered array of integers to evaluate        |

**Example:**
```json
{ "numbers": [-2, 1, -3, 4, -1, 2, 1, -5, 4] }
```

---

### 2.2 Response — `SubarrayResponse`

| Field     | Type     | Description                                                       |
|-----------|----------|-------------------------------------------------------------------|
| `maxSum`  | `int`    | The maximum sum of any contiguous subarray. Minimum value: `0`   |
| `message` | `String` | Human-readable status: `"Calculation complete"`, `"Array is Empty"`, `"All numbers are negative"` |

**Example:**
```json
{ "maxSum": 6, "message": "Calculation complete" }
```

---

## 3. UML Sequence Diagrams

### 3.1 Functional Sequence 

```
  Consumer                    Maximum Subarray API
     │                                │
     │   Submit integer array         │
     │───────────────────────────────>│
     │                                │
     │                        Validate input
     │                        ┌───────┴───────┐
     │                        │  null / empty? │──── Yes ──> return 0 ("Array is Empty")
     │                        └───────┬───────┘
     │                        All negative?
     │                        ┌───────┴───────┐
     │                        │  all n < 0?   │──── Yes ──> return 0 ("All negative")
     │                        └───────┬───────┘
     │                        Compute maximum subarray sum (Kadane's algorithm)
     │                                │
     │   maxSum + status message      │
     │<───────────────────────────────X
```

---

### 3.2 Technical Sequence

```
  HTTP Client          MaximumSubarrayController       CalculationService
      │                          │                             │
      │  POST /api/maximum/subarray                            │
      │  Body: { "numbers": [...] }                            │
      │─────────────────────────>│                             │
      │                          │                             │
      │                          │ calculationService.max(req) │
      │                          │────────────────────────────>│
      │                          │                             │
      │                          │          return SubarrayResponse(0, "All numbers are negative")
      │                          │<────────────────────────────│
      │                          │                             │
      │                          │          return SubarrayResponse(0, "Array is Empty")
      │                          │<────────────────────────────│
      │                          │                             │
      │                          │          return SubarrayResponse(maxSum, "Calculation complete")
      │                          │<────────────────────────────│
      │                          │                             │
      │  HTTP 200 OK + maxSum    │                             │
      │<─────────────────────────│                             │
      │                          │                             │
      │ ALT:HTTP 400 Bad Request │                             │
      │<─────────────────────────X                             X
```

---

## 4. API Documentation  // API contract information

### Base URL

```
http://localhost:8080
```

> Swagger UI is available at: `http://localhost:8080/swagger-ui/index.html`

---

### POST `/api/maximum/subarray`

Calculate the maximum sum of a contiguous subarray.

#### Request

| Property       | Value                   |
|----------------|-------------------------|
| Method         | `POST`                  |
| Path           | `/api/maximum/subarray` |
| Content-Type   | `application/json`      |

**Body Schema:**

```json
{
  "numbers": [integer]
}
```

| Field     | Type    | Required | Notes                        |
|-----------|---------|----------|------------------------------|
| `numbers` | `int[]` | Yes      | May be empty `[]`; not null  |

---

#### Responses

| Status | Condition                        | Body                                                    |
|--------|----------------------------------|---------------------------------------------------------|
| `200`  | Successful calculation           | `{ "maxSum": <int>, "message": "<string>" }`            |
| `400`  | Null body or unhandled exception | Empty body                                              |

---

#### Example Requests & Responses

**Mixed array (canonical case):**
```bash
curl -X POST http://localhost:8080/api/maximum/subarray \
  -H "Content-Type: application/json" \
  -d '{"numbers": [-2, 1, -3, 4, -1, 2, 1, -5, 4]}'
```
```json
{ "maxSum": 6, "message": "Calculation complete" }
```

**All-positive array:**
```bash
curl -X POST http://localhost:8080/api/maximum/subarray \
  -H "Content-Type: application/json" \
  -d '{"numbers": [1, -1, 5]}'
```
```json
{ "maxSum": 5, "message": "Calculation complete" }
```

**Empty array:**
```bash
curl -X POST http://localhost:8080/api/maximum/subarray \
  -H "Content-Type: application/json" \
  -d '{"numbers": []}'
```
```json
{ "maxSum": 0, "message": "Array is Empty" }
```

**All-negative array:**
```bash
curl -X POST http://localhost:8080/api/maximum/subarray \
  -H "Content-Type: application/json" \
  -d '{"numbers": [-3, -1, -2]}'
```
```json
{ "maxSum": 0, "message": "All numbers are negative" }
```

**Null body → 400:**
```bash
curl -X POST http://localhost:8080/api/maximum/subarray \
  -H "Content-Type: application/json" \
  -d '{}'
```
```
HTTP 400 Bad Request  (empty body)
```

---