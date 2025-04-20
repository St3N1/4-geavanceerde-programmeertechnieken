function moveEnemy(enemy, player)
    if enemy.x < player.x then
        enemy.x = enemy.x + 1
    elseif enemy.x > player.x then
        enemy.x = enemy.x - 1
    end
end