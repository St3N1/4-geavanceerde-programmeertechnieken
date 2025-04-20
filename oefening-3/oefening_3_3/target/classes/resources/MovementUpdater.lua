function update(components)
    for i = 1, #components do
        components[i]["vx"] = components[i]["vx"] + components[i]["ax"]
        components[i]["vy"] = components[i]["vy"] + components[i]["ay"]

        print(components[i], i, "updated")
        print("vx - ", components[i]["vx"])
        print("vy - ", components[i]["vy"])
        print("ax - ", components[i]["ax"])
        print("ay - ", components[i]["ay"])
    end
    return components
end