maps = function() {
    for(i = 0; i < this.credit.length; i++) {
        emit(this.credit[i].currency, parseFloat(this.credit[i].balance))
    }
}

reduces = function(currency, values) {
    return Array.sum(values)
}

db.people.mapReduce(
    maps,
    reduces,
    {
        out: "task2",
    }
    );

printjson(db.task2.find().toArray());
