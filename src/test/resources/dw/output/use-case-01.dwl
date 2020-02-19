%dw 2.0
input payload application/csv  header=false, separator="|"
output application/xml  
---
{
  root: {
    (payload map {
      item: {
        field1: $[0],
        field2: $[1],
        field3: $[2],
        field4: $[3]
      }
    })
  }
}