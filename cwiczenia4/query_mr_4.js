maps = function() {
    let height = parseFloat(this.height);
    let bmi = parseFloat(this.weight)/height*height;
    emit(this.nationality, bmi)
}

reduces = function(currency, values) {
    return {
    "average": Array.sum(values)/values.length,
    "minimum":Math.min(...values),
    "maximum":Math.max(...values)
}}

db.people.mapReduce(
    maps,
    reduces,
    {
        out: "task4",
    });

printjson(db.task4.find().toArray());

