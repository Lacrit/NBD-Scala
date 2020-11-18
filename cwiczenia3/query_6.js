db.people.insert(
    {
        "birth_date":"2000-01-01T12:59:00Z",
        "credit": [
            {"type": "diners-club-enroute", "number": "3575871120833093", "currency": "IDR", "balance": "4956.38"}, {"type": "china-unionpay", "number": "5354193104951706", "currency": "CAD", "balance": "6119.68"}, {"type": "diners-club-us-ca", "number": "491107956763520687", "currency": "PLN", "balance": "3445.98"}, {"type": "bankcard", "number": "3543536845551440", "currency": "HNL", "balance": "4255.17"}, {"type": "bankcard", "number": "201814967368342", "currency": "PLN", "balance": "5036.1"}
            ],
        "description": "blabla",
        "email": "s15486@pjwstk.edu.pl",
        "height":"174",
        "job":"Senior HTML Programmer",
        "first_name":"Anna",
        "last_name":"Voitenkova",
        "location":{"city": "Warsaw", "address": {"streetname": "Grochow", "streetnumber": "666"}},
        "nationality":"Poland",
        "sex":"Female",
        "weight":1234
    })
printjson(db.people.findOne({email:"s15486@pjwstk.edu.pl"}))
