import cobra.test
import numpy as np

def substrateFinder(metabolites, directions, metabolic_model):
    metabolites = metabolites[0]
    directions = np.array(directions)
    metabolic_model = str(metabolic_model)

    solver = 'glpk'
    cobra.test.create_test_model(metabolic_model)
    model = cobra.test.create_test_model(metabolic_model)
    v_out = np.zeros(len(metabolites))

    for i in range(len(metabolites)):
        if directions[i] == 0:
            v_out[i] = 0
        else:
            for j in range(len(metabolites)):
                if directions[j] != 0:
                    if directions[j] == 1:
                        model.reactions.get_by_id(metabolites[j]).lower_bound = 0
                    else:
                        model.reactions.get_by_id(metabolites[j]).upper_bound = 0

            if directions[i] == 1:
                model.reactions.get_by_id(metabolites[i]).lower_bound = -20
            elif directions[i] == -1:
                model.reactions.get_by_id(metabolites[i]).upper_bound = 20

            try:
                s = model.optimize()
                miu = s.objective_value
                v_out[i] = miu
            except:
                v_out[i] = 0

    print(v_out)