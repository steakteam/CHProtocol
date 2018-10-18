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
