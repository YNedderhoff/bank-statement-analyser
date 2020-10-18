# bank-statement-analyser

![Build, Test and Publish](https://github.com/ynedderhoff/bank-statement-analyser/workflows/Build,%20Test%20and%20Publish/badge.svg)

## Requests

### Upload/Download files

#### Upload single file

```bash
curl --request POST 'localhost:8080/uploadFile' \
    --form 'file=@/path/to/file.pdf'
```

#### Upload multiple files

```bash
curl --request POST 'localhost:8080/uploadMultipleFiles' \
--form 'files=@/path/to/file1.pdf' \
--form 'files=@/path/to/file2.pdf' \
--form 'files=@/path/to/file3.pdf'
```

#### Download files

```bash
curl --request GET 'http://localhost:8080/downloadFile/file.pdf'
```