# CHProtocol

ProtocolLib wrapper for CommandHelper

## Functions
### packet_create
packet_create(protocol, side, name)
### packet_read
packet_read([packet], index)
### packet_write
packet_write([packet], index, value)
### packet_send
packet_send([player], packet)
### packet_info
packet_info([packet])
### all_packets
all_packets()

## Events
### packet_event
protocol, side, name, packet

## Example
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
        packet_write(@e[packet], 0, colorize('&cHello CHProtocol!'))
    }
}
```
