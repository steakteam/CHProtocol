# CHProtocol

ProtocolLib wrapper for CommandHelper

## Functions
### create_packet
create_packet(protocol, side, name)
### packet_read
packet_read([packet], index)
### packet_write
packet_write([packet], index, value)
### send_packet
send_packet([player], packet)
### packet_info
packet_info([packet])
### all_packets
all_packets()

## Events
### packet_event
protocol, side, name, player, packet

## Example

### Chat

```javascript
bind(player_join, null, null, @e) {
    @packet = create_packet('play', 'out', 'chat')
    packet_write(@packet, 0, 'Hello')
    packet_write(@packet, 2, 'GAME_INFO')
    send_packet(@packet)
}

bind(packet_event, null, array(name: CHAT), @e) {
    @info = packet_read(@e[packet], 2)
    if (@info == 'GAME_INFO') {
        packet_write(0, colorize('&cHello CHProtocol!'))
    }
}
```

### WorldBorder

#### aliases.msa

```javascript
*:/wb = >>>
    @loc = ploc()
    @packet = create_packet('play', 'out', 'worldBorder') // create a worldborder packet
    packet_write(@packet, 0, 'SET_CENTER') // marking to `set center`
    packet_write(@packet, 1, 29999984) // No idea but the server sent this
    _set_wb_size(@packet, 20) // world border size
    _set_wb_center(@packet, 'world', @loc[x], @loc[z]) // worldborder center
    packet_write(@packet, 7, 15) // maybe warning related
    packet_write(@packet, 8, 5) // maybe warning related
    send_packet(@packet) // send the packet
    set_timeout(0, closure() { // delay 1 tick, because without this doesn't work
        packet_write(@packet, 0, 'SET_SIZE') // remarking to `set_size`
        send_packet(@packet) // resend the packet
    })
<<<
```

#### auto_include.ms

```javascript
proc _set_wb_size(@packet, @size) {
    packet_write(@packet, 4, @size)
    packet_write(@packet, 5, @size)
}

proc _set_wb_center(@packet, @world, @x, @z) {
    @modifier = if (world_info(@world)[environment] == 'NETHER'
        , 8
        , 1)
    packet_write(@packet, 2, @x * @modifier)
    packet_write(@packet, 3, @z * @modifier)
}
```
