# Supervise Mobile API Handoff (v1)

## Files to Share
- Postman Collection: `docs/api/postman/Supervise_API_v1.postman_collection.json`
- Postman Env (Local): `docs/api/postman/Supervise_API_v1.local.postman_environment.json`
- Postman Env (Staging): `docs/api/postman/Supervise_API_v1.staging.postman_environment.json`

## Import Steps (Postman)
1. Open Postman → **Import**.
2. Import the collection JSON.
3. Import one environment JSON (local or staging).
4. Select the imported environment in top-right environment selector.
5. Set `base_url`, `identifier`, and `password` as needed.

## Base URL Convention
- Local: `http://127.0.0.1:8000`
- Staging: `https://staging.your-domain.com`

All endpoints below are prefixed by: `/api/v1`.

## Staging Docs Access (Scramble WebUI)
- Docs UI URL: `https://subot.gilarya.my.id/docs/api`
- OpenAPI JSON URL: `https://subot.gilarya.my.id/docs/api.json`
- Access control: both URLs are currently protected by `web + auth:admin`.

Steps:
1. Open `https://subot.gilarya.my.id/login/admin`.
2. Login with internal admin account.
3. Open `https://subot.gilarya.my.id/docs/api`.
4. Use `POST /api/v1/auth/login` in Try It, copy `access_token`, then click **Authorize** for protected API testing.

## Standard Response Format
### Success
```json
{
  "status": "success",
  "message": "...",
  "data": {}
}
```

### Error
```json
{
  "status": "error",
  "message": "...",
  "errors": {}
}
```

## Endpoint Contract

### 1) Public Schools (Paginated)
- **GET** `/public/schools`
- Auth: **No auth**
- Query Params:
  - `page` (optional, default paginator behavior)
  - `per_page` (optional, default `10`, min `1`, max `100`)

Example:
`GET /api/v1/public/schools?page=1&per_page=10`

Example response data:
```json
{
  "items": [
    { "id": 1, "sekolah": "SMA A" },
    { "id": 2, "sekolah": "SMP B" }
  ],
  "pagination": {
    "current_page": 1,
    "last_page": 3,
    "per_page": 10,
    "total": 22,
    "from": 1,
    "to": 10,
    "has_more_pages": true,
    "next_page_url": "https://.../api/v1/public/schools?page=2&per_page=10",
    "prev_page_url": null
  }
}
```

### 2) Login
- **POST** `/auth/login`
- Auth: **No auth**
- Headers: `Accept: application/json`, `Content-Type: application/json`

Body:
```json
{
  "login_as": "member",
  "identifier": "NIS_OR_SEKOLAH",
  "password": "YOUR_PASSWORD",
  "device_name": "mobile-device-name"
}
```

Rules:
- `login_as = member` → `identifier` must be member `nis`, with active status.
- `login_as = mitra` → `identifier` must be `sekolah`, with active status.

Success response data:
```json
{
  "token_type": "Bearer",
  "access_token": "...",
  "user_type": "member",
  "user": {
    "type": "member",
    "profile": {
      "id": 123,
      "role": "member",
      "nis": "12345",
      "nama_lengkap": "Budi Santoso",
      "id_sekolah": "SCH-01",
      "kelas": "6A",
      "tempat_lahir": "Jakarta",
      "tanggal_lahir": "2014-05-12",
      "alamat": "Jl. Contoh",
      "telephone": "08123456789",
      "status_siswa": "Aktif"
    }
  }
}
```

### 3) Auth Me
- **GET** `/auth/me`
- Auth: `Authorization: Bearer <access_token>`
- Returns current authenticated user profile wrapper.

### 4) Logout
- **POST** `/auth/logout`
- Auth: `Authorization: Bearer <access_token>`
- Revokes current token only.

### 5) Profile Me
- **GET** `/profile/me`
- Auth: `Authorization: Bearer <access_token>`
- Returns same profile wrapper used by auth endpoints.

### 6) Profile Members (Mitra Only, Paginated)
- **GET** `/profile/members`
- Auth: `Authorization: Bearer <access_token>`
- Authorization: only `mitra` role can access this endpoint.
- Query Params:
  - `page` (optional)
  - `per_page` (optional, default `10`, min `1`, max `100`)
  - `search` (optional; matches `nama_lengkap` or `nis`)

Example:
`GET /api/v1/profile/members?page=1&per_page=10&search=`

Example response data:
```json
{
  "items": [
    {
      "id": 10,
      "role": "member",
      "nama_lengkap": "Budi",
      "nis": "12345",
      "kelas": "6A",
      "id_sekolah": "SCH-01",
      "status_siswa": "Aktif",
      "profile_image_url": "http://127.0.0.1:8000/storage/assets/data/dataAnak/img/pasFoto_budi_20260311_144844.png"
    }
  ],
  "pagination": {
    "current_page": 1,
    "last_page": 4,
    "per_page": 10,
    "total": 31,
    "from": 1,
    "to": 10,
    "has_more_pages": true,
    "next_page_url": "https://.../api/v1/profile/members?page=2&per_page=10&search=",
    "prev_page_url": null
  }
}
```

### 7) Update Penanggung Jawab (Mitra Only)
- **PUT** `/profile/penanggung-jawab`
- Auth: `Authorization: Bearer <access_token>`
- Authorization: only `mitra` role can access this endpoint.
- Headers: `Accept: application/json`, `Content-Type: application/json`

Body:
```json
{
  "nama_penanggung_jawab": "Bapak Ahmad",
  "email_penanggung_jawab": "ahmad@example.com",
  "telephone_penanggung_jawab": "081234567890"
}
```

Example response data:
```json
{
  "profile": {
    "id": 5,
    "role": "mitra",
    "id_sekolah": "SCH-01",
    "sekolah": "SMP Negeri 1",
    "alamat": "Jl. Contoh 123",
    "nama_penanggung_jawab": "Bapak Ahmad",
    "email_penanggung_jawab": "ahmad@example.com",
    "telephone_penanggung_jawab": "081234567890",
    "status": "isactive"
  }
}
```

### 8) Dashboard Summary
- **GET** `/dashboard`
- Auth: `Authorization: Bearer <access_token>`
- Returns metrics, schedule previews, and unpaid invoices summary.

Example response data:
```json
{
  "user": {
    "display_name": "Budi Santoso",
    "type": "member",
    "profile_image_url": "..."
  },
  "summary_metrics": {
    "totalSchedules": 10,
    "completedSchedules": 2,
    "remainingSchedules": 8,
    "nextSchedule": {
      "dateLabel": "28 Apr 2026",
      "timeRange": "08:00 - 10:00",
      "program": "Robotics Level 1",
      "trainer": "Kak Ahmad"
    }
  },
  "schedule_preview": [
    {
      "id": 1,
      "program": "Robotics Level 1",
      "trainer": "Kak Ahmad",
      "date": "2026-04-28",
      "start_time": "08:00:00",
      "end_time": "10:00:00",
      "time_range": "08:00 - 10:00",
      "date_label": "28 Apr 2026",
      "status": "terjadwal",
      "status_label": "Terjadwal",
      "status_badges": ["terjadwal"],
      "row_classes": "bg-gray-50"
    }
  ],
  "unpaid_invoices": {
    "items": [
      {
        "id": 123,
        "label": "Cicilan ke-1",
        "description": "Budi Santoso • Robotics Level 1",
        "amount": 150000,
        "amount_formatted": "Rp 150.000",
        "due_date_formatted": "30 Apr 2026"
      }
    ],
    "total_amount": 150000,
    "total_formatted": "Rp 150.000",
    "has_more": false,
    "remaining_count": 0
  }
}
```

### 9) Schedules List
- **GET** `/schedules`
- Auth: `Authorization: Bearer <access_token>`
- Returns list of schedules similar to `schedule_preview` in dashboard.

### 10) Schedule Detail
- **GET** `/schedules/{id}`
- Auth: `Authorization: Bearer <access_token>`
- Returns full details of a specific schedule including students/attendance.

Example response data:
```json
{
  "scheduleContext": "member",
  "schedule": {
    "id": 1,
    "dateLabel": "28 April 2026",
    "program": "Robotics Level 1",
    "level": "Basic",
    "classroom": "Grade 6A",
    "trainer": "Kak Ahmad",
    "timeRange": "08:00 - 10:00",
    "statusBadges": ["terjadwal"],
    "notes": "Pertemuan pertama pengenalan komponen.",
    "students": [
      {
        "id": 10,
        "nis": "12345",
        "name": "Budi Santoso",
        "absensi_status": "Hadir",
        "absensi_label": "Hadir",
        "row_class": "bg-emerald-50 hover:bg-emerald-100"
      }
    ]
  }
}
```

### 11) Unpaid Invoices (Full List)
- **GET** `/finance/invoices`
- Auth: `Authorization: Bearer <access_token>`
- Returns all unpaid invoices for current user.

### 12) Payment History (Paginated)
- **GET** `/finance/history`
- Auth: `Authorization: Bearer <access_token>`
- Query Params: `page`, `per_page`
- Returns paginated list of paid invoices.

Example response data:
```json
{
  "items": [
    {
      "id": 10,
      "nomor_tagihan": "INV-2026-001",
      "total_tagihan": 150000,
      "status": "Lunas",
      "jatuh_tempo": "2026-04-30",
      "tipe_tagihan": "individu",
      "program": "Robotics Level 1"
    }
  ],
  "pagination": {
    "current_page": 1,
    "last_page": 1,
    "per_page": 10,
    "total": 1,
    "has_more_pages": false
  }
}
```

### 13) Request Payment Token (Midtrans)
- **POST** `/finance/request-token`
- Auth: `Authorization: Bearer <access_token>`
- Body:
```json
{
  "tagihan_id": 123
}
```

Example response data:
```json
{
  "token": "snap-token-xyz-123"
}
```

### 14) Supervise Raw Profile Test
- **GET** `/supervise`
- Auth: `Authorization: Bearer <access_token>`
- Returns raw generic user object. Used mainly for quick sanity check and testing auth mechanism.

## Error/Status Matrix per Endpoint

| Endpoint | Method | 200 | 401 | 422 | 429 | 500 |
|---|---|---|---|---|---|---|
| `/public/schools` | GET | Success + paginated schools list | - | - | Too many requests | Unexpected server error |
| `/auth/login` | POST | Login success + bearer token | - | Validation failed | Too many attempts | Unexpected server error |
| `/dashboard` | GET | Dashboard data | Missing token | - | Too many requests | Unexpected server error |
| `/schedules` | GET | Schedules list | Missing token | - | Too many requests | Unexpected server error |
| `/schedules/{id}` | GET | Schedule detail | Missing token | - | Too many requests | Unexpected server error |
| `/finance/invoices` | GET | Unpaid invoices | Missing token | - | Too many requests | Unexpected server error |
| `/finance/history` | GET | Payment history | Missing token | - | Too many requests | Unexpected server error |
| `/finance/request-token` | POST | Midtrans Snap Token | Missing token | Validation failed | Too many requests | Unexpected server error |
| `/profile/me` | GET | Current profile | Missing token | - | Too many requests | Unexpected server error |
| `/supervise` | GET | Raw user profile test | Missing token | - | Too many requests | Unexpected server error |

### Error Cases (Current Behavior)

#### `POST /auth/login`
- `422` when request validation fails (`login_as`, `identifier`, `password` invalid/missing).
- `422` when credential check fails (`Kredensial tidak valid.`).

#### Sanctum-protected endpoints (`/auth/me`, `/auth/logout`, `/profile/me`)
- `401` when:
  - `Authorization` header missing,
  - bearer token invalid,
  - bearer token already revoked,
  - bearer token expired (token lifetime is 3 days / 4320 minutes from creation).

#### `GET /profile/members`
- `403` when authenticated user is not `mitra` role.

#### `PUT /profile/penanggung-jawab`
- `403` when authenticated user is not `mitra` role.
- `422` when payload validation fails (`nama_penanggung_jawab`, `email_penanggung_jawab`, `telephone_penanggung_jawab`).

### Notes
- Current token lifetime: **3 days** (`SANCTUM_EXPIRATION=4320`).
- Active rate limit config (env-driven):
  - `RATE_LIMIT_SUPERVISE_PUBLIC_SCHOOLS=60`
  - `RATE_LIMIT_SUPERVISE_AUTH_LOGIN=10`
  - `RATE_LIMIT_SUPERVISE_AUTH_LOGIN_IP=30`
  - `RATE_LIMIT_SUPERVISE_AUTH_PROTECTED=120`
  - `RATE_LIMIT_API_DEFAULT=60`
- All success/error payloads follow the same wrapper:
  - success: `status`, `message`, `data`
  - error: `status`, `message`, `errors` (if available)

## Mobile Refresh/Relogin Behavior Guidance
- There is currently **no refresh-token endpoint**. Token renewal strategy is **re-login**.
- Recommended client flow:
  1. Save login timestamp when token is issued.
  2. Treat token as near-expiry after ~2 days 23 hours and prepare silent UX prompt for re-login.
  3. On any `401` from protected endpoints, clear local token and redirect to login screen.
  4. After successful login, replace old token and retry the last failed request once.
- Do not retry `401` indefinitely; retry only once after re-authentication.
- Keep logout behavior explicit: call `/auth/logout`, then remove token from secure storage.

## Postman Flow Recommendation
1. Run **Public → Get Schools (Paginated)** for mitra school picker.
2. Run **Auth → Login (Generic)** or one of examples.
3. Token is auto-saved into `access_token` by collection test script.
4. Run **Auth → Me** and **Profile → Profile Me**.
5. Run **Auth → Logout** to clear token.

## Notes for Mobile Team
- Persist `access_token` securely (Keychain/Keystore).
- Always send `Accept: application/json`.
- Handle `401` by forcing relogin (token can be missing, invalid, revoked, or expired).
- Handle `422` for validation/business errors.
- Handle `429` by honoring retry/backoff (use exponential backoff; do not spam retry loop).
- Use `pagination.has_more_pages` or `next_page_url` for infinite scroll.
- Do not assume `id` is secret; it is only an identifier.

## Suggested Next Items to Share with Mobile Team
1. **Error code map**: list of expected status codes per endpoint (`200`, `401`, `422`, `429`, `500`).
2. **Auth lifecycle policy**: token expiry/revocation rules and re-login behavior.
3. **Versioning policy**: how breaking changes are introduced in `/api/v2`.
4. **Staging test accounts**: one member and one mitra account with known credentials.
5. **Changelog**: endpoint changes and deprecations per release.
6. **Rate limit policy**: request limits for public and auth endpoints.

## Rate Limit Policy (Current)
- Public school list (`GET /public/schools`): 60 requests/minute per IP.
- Auth login (`POST /auth/login`):
  - 10 requests/minute per `identifier + IP`.
  - 30 requests/minute per IP (global login burst guard).
- Protected auth/profile (`/auth/me`, `/auth/logout`, `/profile/me`): 120 requests/minute per authenticated user + IP.
- Mitra members list (`GET /profile/members`): 120 requests/minute per authenticated user + IP.
- Update penanggung jawab (`PUT /profile/penanggung-jawab`): 120 requests/minute per authenticated user + IP.
- On limit exceed, API returns `429` with JSON error message.

## Quick Staging Smoke Test
- `GET /api/v1/public/schools`
- `POST /api/v1/auth/login`
- `GET /api/v1/auth/me`
- `GET /api/v1/profile/me`
- `GET /api/v1/supervise`
- `GET /api/v1/profile/members` (login as mitra)
- `PUT /api/v1/profile/penanggung-jawab` (login as mitra)
- `GET /api/v1/dashboard`
- `GET /api/v1/schedules`
- `GET /api/v1/schedules/{id}`
- `GET /api/v1/finance/invoices`
- `GET /api/v1/finance/history`
- `POST /api/v1/finance/request-token`
- `POST /api/v1/auth/logout`
