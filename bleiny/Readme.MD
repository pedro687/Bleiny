**Rotas**
context-path: /api/bleiny

**User**

Criar uma conta de usuário:

- POST `/user/register`
- Body:

```json
{
  "username": "pedrospiet",
  "email": "pedrospiset@gmail.com",
  "password": "12345",
  "tellphone": "12345678941",
  "address":
  {
    "uf": "SP",
    "city": "Itanhaém",
    "country": "Brazil"
  }
}
```
