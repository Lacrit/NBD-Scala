maps = function() {
    for(i = 0; i < this.credit.length; i++) {
        emit(this.credit[i].currency, parseFloat(this.credit[i].balance))
    }
}

reduces = function(currency, values) {
    return {"balance_left":Array.sum(values), "average_left":Array.sum(values)/values.length}
}

db.people.mapReduce(
    maps,
    reduces,
    {
        query: {nationality:"Poland", sex:"Female"},
        out: "task5",
    }
    );

printjson(db.task5.find().toArray());
