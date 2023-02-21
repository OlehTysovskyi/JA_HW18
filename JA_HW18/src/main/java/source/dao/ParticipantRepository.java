package source.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import source.domain.Participant;

@Repository
public class ParticipantRepository {

	private List<Participant> participants = new ArrayList<>();

	@PostConstruct
	public void init() {
		Participant participant1 = new Participant(1, "Oleh", "olehtysyk@gmail.com", 4, "medium");
		Participant participant2 = new Participant(2, "Bodya", "bodik@gmail.com", 5, "high");
		Participant participant3 = new Participant(3, "Vitalik", "vit@gmail.com", 2, "low");

		participants.add(participant1);
		participants.add(participant2);
		participants.add(participant3);
	}

	public List<Participant> findAllParticipants() {
		return participants;
	}

	public Participant findOne(int id) {
		return participants.stream().filter(participants -> participants.getId() == id).findFirst().orElse(null);
	}

	public void save(Participant participant) {
		Participant participantUpdate = null;

		if (participant.getId() != null) {
			participantUpdate = findOne(participant.getId());
			int bookIndex = participants.indexOf(participantUpdate);
			participantUpdate.setName(participant.getName());
			participantUpdate.setEmail(participant.getEmail());
			participantUpdate.setLevel(participant.getLevel());
			participantUpdate.setPrimarySkill(participant.getPrimarySkill());
			participants.set(bookIndex, participantUpdate);
		} else {
			participant.setId(participants.size() + 1);
			participants.add(participant);
		}

	}

	public void delete(int id) {
		Iterator<Participant> iter = participants.iterator();
		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				iter.remove();
			}
		}

	}

}
