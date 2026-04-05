# Domaći 1 - Quotes App

Java aplikacija za čuvanje i pregled citata, realizovana kao dva HTTP servisa koji komuniciraju putem Java Socket-a.

## Opis

Klijent može da unese citat i njegovog autora, pregleda sve sačuvane citate, kao i da vidi citat dana koji se prikazuje uz formu za unos.

## Arhitektura

Sistem se sastoji od dva servisa:

- **Glavni servis** (port 8080) — prima zahteve od klijenta, čuva citate i prikazuje ih
- **Pomoćni servis** (port 8081) — interni servis, vraća random citat dana u JSON formatu

Komunikacija između servisa je realizovana pomoću Java Socket-a (bez gotovih HTTP klijent biblioteka). Za parsiranje JSON-a korišćena je `gson` biblioteka.

## Endpointi

| Method | Putanja | Opis |
|--------|---------|------|
| GET | `/quotes` | Prikazuje formu za unos, citat dana i listu sačuvanih citata |
| POST | `/save-quote` | Čuva novi citat i redirektuje na `/quotes` |
| GET | `/quote-of-the-day` | (pomoćni servis) Vraća random citat u JSON formatu |

## Pokretanje

1. Pokrenuti **QuoteOfTheDayServer** (`app` paket) — pomoćni servis na portu 8081
2. Pokrenuti **Server** (`http` paket) — glavni servis na portu 8080
3. Otvoriti browser na `http://localhost:8080/quotes`

## Tehnologije

- Java
- Java Sockets (HTTP komunikacija između servisa)
- Gson 2.8.2 (JSON parsiranje)