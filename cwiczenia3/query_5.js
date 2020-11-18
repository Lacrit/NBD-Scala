printjson(db.people.find({ "birth_date": {"$regex": "^(200[1-9])|(20[1-9][0-9])"}}).toArray());

