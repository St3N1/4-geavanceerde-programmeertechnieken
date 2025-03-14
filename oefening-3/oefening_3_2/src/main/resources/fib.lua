function fib(n)
    v_n=0
    v_n_1=1

    for i = 0, n do
        new = v_n + v_n_1
        v_n = v_n_1
        v_n_1 = new
    end

    return v_n
end