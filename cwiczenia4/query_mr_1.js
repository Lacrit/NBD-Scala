maps = function() {
    emit(this.sex, {height: this.height, weight: this.weight})
}

reduces = function(sex, values) {
    var sumH = 0;
    var sumW = 0;
    for (s in values) {
        sumH += parseFloat(values[s].height)
        sumW += parseFloat(values[s].weight)
    }
    return { "Height average" : sumH/values.length, "Weight average": sumW/values.length};
}

db.people.mapReduce(
    maps,
    reduces,
    {
        out: "task1",
    }
    );

printjson(db.task1.find().toArray());
