### SOAP WEBSERVICE JAX-WS
### DATABASE: db_bank_pro

### Login
- melakukan validasi ID yang diinput oleh user untuk login ke Bank Pro
- Table: account_tbl

### Transfer
- melakukan pengecekan detail input form yang dimasukkan user dan mengupdate database apabila transfer berhasil dilakukan
- mengembalikan status transfer
- Table: account_tbl, account_transaction_tbl, virtual_account_tbl

### GenerateVA
- mengembalikan virtual account untuk pembelian tiket Engima
- mengupdate database dengan entry virtual account yang dihasilkan
- Table: virtual_account_tbl

### AccountHistory
- mengembalikan matriks berisi tabel riwayat transaksi user Bank Pro untuk ditampilkan di Transaction History Bank Pro
- Table: account_tbl, account_transaction_tbl, virtual_account_tbl

### GetVirtualAccount
- mengembalikan list seluruh virtual account di database Bank Pro
- Table: virtual_account_tbl

### CheckCredit
- mengembalikan status pembayaran sebuah transaksi di WS Transaksi dengan melakukan pengecekan apabila ada pembayaran yang sesuai
- Table: account_transaction_tbl


### PEMBAGIAN KERJA
- CI / CD : 13517131, 13515116, 13517008
- Eksplorasi dan setup mesin deployment : 13517131
- Linting : 13517131, 13515116, 13517008

### WS-BANK     : 54.210.33.100:8080/ws-bank